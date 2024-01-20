package com.isyundong.recommendation.toolbox.openSearch;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public abstract class OpenSearchBaseIndex implements Serializable {

    @Serial
    private static final long serialVersionUID = 7936716328133179898L;

    private String _id;

    private float queryScore;

}
