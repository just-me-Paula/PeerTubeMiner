package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Comment;
import com.Peertube.Miner.model.peertube.CommentSearch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Value("${peertube.baseUri}")
    private String peertubeBaseUri;

    private final RestTemplate restTemplate;

    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Comment> fetchCommentsByVideo(String videoUuid, int maxComments) {
        String url = peertubeBaseUri + "/videos/" + videoUuid + "/comment-threads";
        CommentSearch response = restTemplate.getForObject(url, CommentSearch.class);
        if (response == null || response.getData() == null) {
            return new ArrayList<>();
        }
        List<Comment> comments = response.getData();
        if (comments.size() > maxComments) {
            return comments.subList(0, maxComments);
        }
        return comments;
    }
}