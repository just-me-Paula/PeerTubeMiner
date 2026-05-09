package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Video;
import com.Peertube.Miner.model.peertube.VideoSearch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Value("${peertube.baseUri}")
    private String peertubeBaseUri;

    private final RestTemplate restTemplate;

    public VideoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Video> fetchVideosByChannel(String channelHandle, int maxVideos) {
        String url = peertubeBaseUri + "/video-channels/" + channelHandle + "/videos?count=" + maxVideos;
        VideoSearch response = restTemplate.getForObject(url, VideoSearch.class);
        if (response == null || response.getData() == null) {
            return new ArrayList<>();
        }
        List<Video> videos = response.getData();
        if (videos.size() > maxVideos) {
            return videos.subList(0, maxVideos);
        }
        return videos;
    }
}