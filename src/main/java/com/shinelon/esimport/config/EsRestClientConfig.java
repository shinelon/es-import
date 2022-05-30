package com.shinelon.esimport.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.time.Duration;

/**
 * @author Shinelon
 */
@Configuration
@Slf4j
public class EsRestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${es.host}")
    private String esUris;

    @Bean
    public ElasticsearchDataAutoConfiguration elasticsearchDataAutoConfiguration() {
        return new ElasticsearchDataAutoConfiguration();
    }

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final String[] split = StringUtils.split(esUris, ",");
        ClientConfiguration clientConfiguration =
                ClientConfiguration.builder().connectedTo(split)
                        .withSocketTimeout(Duration.ofSeconds(90L))
                        .withConnectTimeout(Duration.ofSeconds(90L)).build();
        final RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        log.info("######################RestHighLevelClient config uris:{}", esUris);
        log.info("######################RestHighLevelClient config success######################");
        return client;

    }
}
