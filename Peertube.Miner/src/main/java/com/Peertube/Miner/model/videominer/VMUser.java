package com.Peertube.Miner.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VMUser {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_link")
    private String user_link;

    @JsonProperty("picture_link")
    private String picture_link;

    public VMUser() {}

    public VMUser(String id,String name, String user_link, String picture_link) {
        this.id = id;
        this.name = name;
        this.user_link = user_link;
        this.picture_link = picture_link;
    }

    public String getName() { return name; }
    public String getUser_link() { return user_link; }
    public String getPicture_link() { return picture_link; }
}

