package com.isyundong.recommendation.datacenter.config.openSearch;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.opensearch.client.RestClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class OpenSearchRestConfig {

    private final OpenSearchConfig openSearchConfig;

    private final HashMap<String, RestClient> restClients = new HashMap<>();

    @PostConstruct
    public synchronized void buildOpenSearchRestClients() {
        if (!CollectionUtils.isEmpty(restClients)) {
            return;
        }
        for (OpenSearchConfig.OpenSearchProperties openSearchProperties : openSearchConfig.getInstanceConfigs()) {
            restClients.put(openSearchProperties.getInstanceName(), buildOpenSearchRestClientInstance(openSearchProperties));
        }
    }

    private RestClient buildOpenSearchRestClientInstance(OpenSearchConfig.OpenSearchProperties openSearchProperties) {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(openSearchProperties.getUsername(), openSearchProperties.getPassword())
        );

        return RestClient
                .builder(new HttpHost(openSearchProperties.getHost(), Integer.parseInt(openSearchProperties.getPort())))
                .build();
    }

}
