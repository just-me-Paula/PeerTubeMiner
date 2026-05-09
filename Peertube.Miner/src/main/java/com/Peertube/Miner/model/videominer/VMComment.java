package com.Peertube.Miner.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VMComment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("createdOn")
    private String createdOn;

    public VMComment() {}

    public VMComment(String id, String text, String createdOn) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
    }

    public String getId() { return id; }
    public String getText() { return text; }
    public String getCreatedOn() { return createdOn; }
}
