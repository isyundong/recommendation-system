package com.isyundong.recommendation.toolbox.openSearch.param;

import lombok.Getter;

@Getter
public abstract class Param<T> {
    protected T param;
    protected Param() {
    }
    public abstract String toQueryDsl();
    public abstract String paramField();
    public void putParam(T param) {
        this.param = param;
    }
}
