package com.isyundong.recommendation.toolbox;

import com.isyundong.recommendation.toolbox.exception.RecommendationException;

import java.util.Map;
import java.util.Objects;

public class ValidateUtil {

    public static void notEmpty(Map<?, ?> obj, String msg) throws RecommendationException {
        if (Objects.isNull(obj) || obj.isEmpty()) {
            throw new RecommendationException(msg);
        }
    }

    public static void nonNull(Object obj, String msg) throws RecommendationException {
        if (Objects.isNull(obj)) {
            throw new RecommendationException(STR."param is null, message: \{msg}");
        }
    }

    public static void nonBlank(String str, String msg) throws RecommendationException {
        if (Objects.isNull(str) || str.trim().isEmpty()) {
            throw new RecommendationException(STR."param is blank, message: \{msg}");
        }
    }

    public static void isTure(boolean b, String msg) throws RecommendationException {
        if (!b) {
            throw new RecommendationException(STR."param is false, message: \{msg}");
        }
    }

    public static void isFalse(boolean b, String msg) throws RecommendationException {
        if (b) {
            throw new RecommendationException(STR."param is true, message: \{msg}");
        }
    }
}
