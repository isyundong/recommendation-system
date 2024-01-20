package com.isyundong.recommendation.toolbox.openSearch.param;

public class TrackTotalHitsParam extends Param<Boolean> {
    @Override
    public String toQueryDsl() {
        return param.toString();
    }
    @Override
    public String paramField() {
        return "track_total_hits";
    }
    public static TrackTotalHitsParam build(boolean param) {
        TrackTotalHitsParam trackTotalHitsParam = new TrackTotalHitsParam();
        trackTotalHitsParam.putParam(param);
        return trackTotalHitsParam;
    }
}
