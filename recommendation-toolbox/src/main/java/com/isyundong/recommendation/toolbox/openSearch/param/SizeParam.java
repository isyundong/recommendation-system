package com.isyundong.recommendation.toolbox.openSearch.param;

public class SizeParam extends Param<Integer> {
    @Override
    public String toQueryDsl() {
        return param.toString();
    }
    @Override
    public String paramField() {
        return "size";
    }
    public static SizeParam build(java.lang.Integer param) {
        SizeParam sizeParam = new SizeParam();
        sizeParam.putParam(param);
        return sizeParam;
    }
}
