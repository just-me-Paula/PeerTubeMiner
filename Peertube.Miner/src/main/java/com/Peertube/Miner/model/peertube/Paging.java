package com.Peertube.Miner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Paging {

    @JsonProperty("total")
    private int total;

    @JsonProperty("count")
    private int count;

    @JsonProperty("start")
    private int start;

    public int getTotal() { return total; }
    public int getCount() { return count; }
    public int getStart() { return start; }
}