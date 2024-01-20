package com.isyundong.recommendation.toolbox.openSearch.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private int took;
    @JSONField(name = "timed_out")
    private boolean timedOut;
    @JSONField(name = "_shards")
    private Shards _shards;
}
