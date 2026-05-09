package com.Peertube.Miner.service;

import com.Peertube.Miner.model.peertube.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    private CommentService commentService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() throws Exception {
        restTemplate = Mockito.mock(RestTemplate.class);
        commentService = new CommentService(restTemplate);

        var field = CommentService.class.getDeclaredField("peertubeBaseUri");
        field.setAccessible(true);
        field.set(commentService, "https://peertube.tv/api/v1");
    }

    @Test
    void fetchCommentsByVideo_returnsEmptyOnNullResponse() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(null);

        List<Comment> result = commentService.fetchCommentsByVideo("some-uuid", 2);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
