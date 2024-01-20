package com.isyundong.recommendation.toolbox.openSearch.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 5645195798953291994L;
    private String method;
    private String endpoint;
}
