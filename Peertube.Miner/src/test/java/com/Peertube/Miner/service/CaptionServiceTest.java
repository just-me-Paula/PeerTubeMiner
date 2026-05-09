package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Caption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CaptionServiceTest {

    private CaptionService captionService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() throws Exception {
        restTemplate = Mockito.mock(RestTemplate.class);
        captionService = new CaptionService(restTemplate);

        var field = CaptionService.class.getDeclaredField("peertubeBaseUri");
        field.setAccessible(true);
        field.set(captionService, "https://peertube.tv/api/v1");
    }

    @Test
    void fetchCaptionsByVideo_returnsEmptyOnNullResponse() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(null);

        List<Caption> result = captionService.fetchCaptionsByVideo("some-uuid");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
