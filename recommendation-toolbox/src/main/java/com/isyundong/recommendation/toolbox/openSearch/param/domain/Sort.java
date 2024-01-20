package com.isyundong.recommendation.toolbox.openSearch.param.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class Sort {
    private BaseSort baseSort;
    private SortScript sortScript;
    @Getter
    public enum OrderType {
        DESC("desc"),
        ASC("asc");
        private final String type;
        OrderType(String type) {
            this.type = type;
        }
    }
    public static Sort build(BaseSort baseSort, SortScript sortScript) {
        Sort sort = new Sort();
        sort.setBaseSort(baseSort);
        sort.setSortScript(sortScript);
        return sort;
    }
}
