package com.isyundong.recommendation.toolbox.openSearch.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchHit<T extends Serializable> implements Serializable {
    @JSONField(name = "_index")
    private String index;
    @JSONField(name = "_id")
    private String id;
    @JSONField(name = "_score")
    private float score;
    @JSONField(name = "_source")
    private T source;

}
