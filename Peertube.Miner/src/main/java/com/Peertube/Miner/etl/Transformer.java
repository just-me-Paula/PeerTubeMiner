package com.Peertube.Miner.etl;

import com.Peertube.Miner.model.peertube.*;
import com.Peertube.Miner.model.videominer.*;
import com.Peertube.Miner.service.CaptionService;
import com.Peertube.Miner.service.CommentService;
import com.Peertube.Miner.service.VideoService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Transformer {

    private final VideoService videoService;
    private final CommentService commentService;
    private final CaptionService captionService;

    public Transformer(VideoService videoService,
                       CommentService commentService,
                       CaptionService captionService) {
        this.videoService = videoService;
        this.commentService = commentService;
        this.captionService = captionService;
    }

    public VMChannel toVMChannel(Channel channel, String channelHandle, int maxVideos, int maxComments) {

        VMChannel vmChannel = new VMChannel(
                String.valueOf(channel.getId()),
                channel.getDisplayName(),
                channel.getDescription(),
                channel.getCreatedAt()
        );

        List<Video> videos = videoService.fetchVideosByChannel(channelHandle, maxVideos);

        for (Video video : videos) {
            VMVideo vmVideo = buildVMVideo(video, maxComments);
            vmChannel.getVideos().add(vmVideo);
        }

        return vmChannel;
    }

    private VMVideo buildVMVideo(Video video, int maxComments) {

        VMUser vmUser = null;
        if (video.getAccount() != null) {
            User acc = video.getAccount();
            vmUser = new VMUser(
                    acc.getDisplayName(),
                    acc.getProfileUrl(),
                    acc.getAvatarPath()
            );
        }

        VMVideo vmVideo = new VMVideo(
                video.getUuid(),
                video.getName(),
                video.getDescription(),
                video.getPublishedAt(),
                vmUser
        );

        // Obtener comentarios con límite
        List<Comment> comments = commentService.fetchCommentsByVideo(video.getUuid(), maxComments);
        for (Comment c : comments) {
            vmVideo.getComments().add(new VMComment(
                    String.valueOf(c.getId()),
                    c.getText(),
                    c.getCreatedAt()
            ));
        }

        // Obtener captions — language es un objeto {id, label} en PeerTube; extraer el label como String
        List<Caption> captions = captionService.fetchCaptionsByVideo(video.getUuid());
        for (Caption cap : captions) {
            String langLabel = null;
            if (cap.getLanguage() != null) {
                langLabel = cap.getLanguage().getLabel();
            }
            vmVideo.getCaptions().add(new VMCaption(
                    String.valueOf(cap.getId()),
                    cap.getCaptionPath(),
                    langLabel
            ));
        }

        return vmVideo;
    }
}
