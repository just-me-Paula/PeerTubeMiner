package com.Peertube.Miner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Caption {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("captionPath")
    private String captionPath;

    @JsonProperty("language")
    private Language language;

    public Long getId() { return id; }
    public String getCaptionPath() { return captionPath; }
    public Language getLanguage() { return language; }
}
