package com.Peertube.Miner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentSearch {

    @JsonProperty("total")
    private int total;

    @JsonProperty("data")
    private List<Comment> data;

    public int getTotal() { return total; }
    public List<Comment> getData() { return data; }
}
