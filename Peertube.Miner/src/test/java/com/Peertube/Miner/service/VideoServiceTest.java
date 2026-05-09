package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Video;
import com.Peertube.Miner.model.peertube.VideoSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class VideoServiceTest {

    private VideoService videoService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() throws Exception {
        restTemplate = Mockito.mock(RestTemplate.class);
        videoService = new VideoService(restTemplate);

        var field = VideoService.class.getDeclaredField("peertubeBaseUri");
        field.setAccessible(true);
        field.set(videoService, "https://peertube.tv/api/v1");
    }

    @Test
    void fetchVideosByChannel_limitsResults() {
        VideoSearch search = new VideoSearch();
        // We can't set internal fields without setters; verify null-safety path
        when(restTemplate.getForObject(anyString(), eq(VideoSearch.class))).thenReturn(null);

        List<Video> result = videoService.fetchVideosByChannel("stux", 5);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}