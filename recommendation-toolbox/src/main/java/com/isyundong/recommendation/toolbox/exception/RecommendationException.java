package com.isyundong.recommendation.toolbox.exception;

import java.io.Serial;

public class RecommendationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4401928683227747680L;

    public RecommendationException() {
        super();
    }

    public RecommendationException(String message) {
        super(message);
    }

    public RecommendationException(String message, Exception e) {
        super(message, e);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
