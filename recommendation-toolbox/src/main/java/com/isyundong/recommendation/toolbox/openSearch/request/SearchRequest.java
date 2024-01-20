package com.isyundong.recommendation.toolbox.openSearch.request;

import com.isyundong.recommendation.toolbox.openSearch.param.Param;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class SearchRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6714147759644433202L;
    private final List<Param<?>> paramList = new ArrayList<>();
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private Set<String> block = new HashSet<>();
    public void putParam(Param<?> param) {
        if (!block.contains(param.paramField())) {
            paramList.add(param);
            block.add(param.paramField());
            return;
        }
        throw new RuntimeException("duplicate parameters: " + param.paramField());
    }
    public String toQuery() {
        return "{" +
                Strings.join(paramList.stream()
                        .map(this::buildQuery)
                        .filter(Objects::nonNull)
                        .toList(), ',')
                + "}";
    }
    private String buildQuery(Param<?> param) {
        if (Objects.nonNull(param)) {
            return ("\"" + param.paramField() + "\":" + param.toQueryDsl());
        }
        return null;
    }

}
