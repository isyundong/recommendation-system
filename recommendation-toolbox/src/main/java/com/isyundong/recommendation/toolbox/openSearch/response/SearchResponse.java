package com.isyundong.recommendation.toolbox.openSearch.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper=true)
public class SearchResponse<T extends Serializable> extends Response {

    @Serial
    private static final long serialVersionUID = 1965274101008888994L;
    private Hits<T> hits;
}
