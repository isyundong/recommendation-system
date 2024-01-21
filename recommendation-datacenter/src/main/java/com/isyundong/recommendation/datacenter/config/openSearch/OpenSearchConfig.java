package com.isyundong.recommendation.datacenter.config.openSearch;


import com.isyundong.recommendation.datacenter.config.BaseConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "opensearch")
public class OpenSearchConfig extends BaseConfig {

    private List<OpenSearchConfig.OpenSearchProperties> instanceConfigs;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class OpenSearchProperties extends BaseProperties {

        private String host;

        private String port;

        private String username;

        private String password;

    }

}
