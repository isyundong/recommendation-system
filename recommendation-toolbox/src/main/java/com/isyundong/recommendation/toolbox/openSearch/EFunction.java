package com.isyundong.recommendation.toolbox.openSearch;



import com.isyundong.recommendation.toolbox.StringUtils;
import com.isyundong.recommendation.toolbox.exception.RecommendationException;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

@FunctionalInterface
public interface EFunction<T, R> extends Function<T, R>, Serializable {

    default String getFieldName() {
        String methodName = getMethodName();
        if (methodName.startsWith("get")) {
            methodName = methodName.substring(3);
        }
        return StringUtils.initialLetterToLowerCase(methodName);
    }


    default String getMethodName() {
        return getSerializedLambda().getImplMethodName();
    }


    default Class<?> getFieldClass() {
        return getReturnType();
    }


    default SerializedLambda getSerializedLambda() {
        try {
            Method method = getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            return (SerializedLambda) method.invoke(this);
        } catch (Exception e) {
            throw new RecommendationException("getSerializedLambda fail", e);
        }
    }


    default Class<?> getReturnType() {
        try {
            SerializedLambda lambda = getSerializedLambda();
            Class<?> className = Class.forName(lambda.getImplClass().replace("/", "."));
            Method method = className.getMethod(getMethodName());
            return method.getReturnType();
        } catch (Exception e) {
            throw new RecommendationException("getReturnType fail", e);
        }
    }


}

