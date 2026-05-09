package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Channel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Value("${peertube.baseUri}")
    private String peertubeBaseUri;

    private final RestTemplate restTemplate;

    public ChannelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Channel fetchChannelById(String channelHandle) {
        String url = peertubeBaseUri + "/video-channels/" + channelHandle;
        return restTemplate.getForObject(url, Channel.class);
    }
}