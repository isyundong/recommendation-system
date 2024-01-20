package com.isyundong.recommendation.toolbox.openSearch.param;

public class FromParam extends Param<Integer> {
    @Override
    public String toQueryDsl() {
        return param.toString();
    }
    @Override
    public String paramField() {
        return "from";
    }
    public static FromParam build(java.lang.Integer param) {
        FromParam fromParam = new FromParam();
        fromParam.putParam(param);
        return fromParam;
    }
}
