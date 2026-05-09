package com.Peertube.Miner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("url")
    private String profileUrl;

    @JsonProperty("avatarPath")
    private String avatarPath;

    public Long getId() { return id; }
    public String getDisplayName() { return displayName; }
    public String getProfileUrl() { return profileUrl; }
    public String getAvatarPath() { return avatarPath; }
}
