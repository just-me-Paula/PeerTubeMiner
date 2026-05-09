package com.Peertube.Miner.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VMCaption {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("language")
    private String language;

    public VMCaption() {}

    public VMCaption(String id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getLanguage() { return language; }
}
