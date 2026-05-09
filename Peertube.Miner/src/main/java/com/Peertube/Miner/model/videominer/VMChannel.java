package com.Peertube.Miner.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class VMChannel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("createdTime")
    private String createdTime;

    @JsonProperty("videos")
    private List<VMVideo> videos;

    public VMChannel() {}

    public VMChannel(String id, String name, String description, String createdTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.videos = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCreatedTime() { return createdTime; }
    public List<VMVideo> getVideos() { return videos; }
}