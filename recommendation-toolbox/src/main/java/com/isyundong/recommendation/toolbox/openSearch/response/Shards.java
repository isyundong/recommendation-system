package com.isyundong.recommendation.toolbox.openSearch.response;

import lombok.Data;

@Data
public class Shards {

    private int total;
    private int successful;
    private int skipped;
    private int failed;

}
