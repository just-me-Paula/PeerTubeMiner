package com.Peertube.Miner.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class VMVideo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("releaseTime")
    private String releaseTime;

    @JsonProperty("user")
    private VMUser user;

    @JsonProperty("comments")
    private List<VMComment> comments;

    @JsonProperty("captions")
    private List<VMCaption> captions;

    public VMVideo() {}

    public VMVideo(String id, String name, String description, String releaseTime, VMUser user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.user = user;
        this.comments = new ArrayList<>();
        this.captions = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getReleaseTime() { return releaseTime; }
    public VMUser getUser() { return user; }
    public List<VMComment> getComments() { return comments; }
    public List<VMCaption> getCaptions() { return captions; }
}
