package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Caption;
import com.Peertube.Miner.model.peertube.CaptionSearch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptionService {

    @Value("${peertube.baseUri}")
    private String peertubeBaseUri;

    private final RestTemplate restTemplate;

    public CaptionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Caption> fetchCaptionsByVideo(String videoUuid) {
        String url = peertubeBaseUri + "/videos/" + videoUuid + "/captions";
        CaptionSearch response = restTemplate.getForObject(url, CaptionSearch.class);
        if (response == null || response.getData() == null) {
            return new ArrayList<>();
        }
        return response.getData();
    }
}

