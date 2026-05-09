package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ChannelServiceTest {

    private ChannelService channelService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() throws Exception {
        restTemplate = Mockito.mock(RestTemplate.class);
        channelService = new ChannelService(restTemplate);

        // inject @Value field
        var field = ChannelService.class.getDeclaredField("peertubeBaseUri");
        field.setAccessible(true);
        field.set(channelService, "https://peertube.tv/api/v1");
    }

    @Test
    void fetchChannelById_returnsChannel() {
        Channel expected = new Channel();
        when(restTemplate.getForObject(anyString(), eq(Channel.class))).thenReturn(expected);

        Channel result = channelService.fetchChannelById("stux");
        assertNotNull(result);
    }
}
