package com.isyundong.recommendation.toolbox.openSearch.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class Hits<T extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6708276859416442195L;
    private Total total;
    @JSONField(name = "max_score")
    private String maxScore;
    private List<SearchHit<T>> hits;

}
