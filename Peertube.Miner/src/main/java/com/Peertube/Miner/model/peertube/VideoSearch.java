package com.Peertube.Miner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoSearch {

    @JsonProperty("total")
    private int total;

    @JsonProperty("data")
    private List<Video> data;

    public int getTotal() { return total; }
    public List<Video> getData() { return data; }
}

