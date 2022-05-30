package com.shinelon.esimport.service.impl;


import com.alibaba.fastjson2.JSON;
import com.shinelon.esimport.bean.BossCompanyImport;
import com.shinelon.esimport.constants.EsConstants;
import com.shinelon.esimport.service.ICompanyIndexOptService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author Shinelon
 * @date 2022-05-05 10:51
 * <p>
 * 索引 boss_company_import
 * <p>
 * _mapping
 * <p>
 * {
 * "properties": {
 * "enterpriseName": {
 * "type": "text",
 * "analyzer": "ik_max_word",
 * "search_analyzer": "ik_smart"
 * },
 * "nameUsed": {
 * "type": "text",
 * "analyzer": "ik_max_word",
 * "search_analyzer": "ik_smart"
 * } ,
 * "enterpriseAddress": {
 * "type": "text",
 * "analyzer": "ik_max_word",
 * "search_analyzer": "ik_smart"
 * },
 * "businessScope": {
 * "type": "text",
 * "analyzer": "ik_max_word",
 * "search_analyzer": "ik_smart"
 * },
 * "industry": {
 * "type": "text",
 * "analyzer": "ik_max_word",
 * "search_analyzer": "ik_smart"
 * },
 * "registrationStatus": {
 * "type": "text",
 * "analyzer": "ik_max_word",
 * "search_analyzer": "ik_smart"
 * }
 * }
 * }
 * _settings
 * <p>
 * {
 * "analysis":{
 * "analyzer":{
 * "default":{
 * "type":"ik_max_word"
 * },
 * "default_search":{
 * "type":"ik_smart"
 * }
 * }
 * }
 * }
 */
@Service
@Slf4j
public class CompanyIndexOptServiceImpl implements ICompanyIndexOptService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public BulkResponse bulkAdd(List<BossCompanyImport> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        final IndexRequest[] indexRequests = list.stream()
                .map(e -> new IndexRequest(EsConstants.INDEX_COMPANY).source(JSON.toJSONString(e), XContentType.JSON))
                .toArray(size -> new IndexRequest[size]);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(indexRequests);
        bulkRequest.timeout(TimeValue.timeValueSeconds(60L));
        try {
            final BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info("bulk.hasFailures:{}", bulkResponse.hasFailures());
            return bulkResponse;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public AcknowledgedResponse delCompanyIndex(){
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(EsConstants.INDEX_COMPANY);
            AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            log.info("indices.del.ret:{}", JSON.toJSONString(response));
            return response;
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public CreateIndexResponse createCompanyIndex() {
        CreateIndexRequest request = new CreateIndexRequest(EsConstants.INDEX_COMPANY);
        request.mapping("{\n" +
                "        \"properties\": {\n" +
                "            \"enterpriseName\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"search_analyzer\": \"ik_smart\"\n" +
                "            },\n" +
                "               \"nameUsed\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"search_analyzer\": \"ik_smart\"\n" +
                "            } ,\n" +
                "               \"enterpriseAddress\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"search_analyzer\": \"ik_smart\"\n" +
                "            },\n" +
                "               \"businessScope\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"search_analyzer\": \"ik_smart\"\n" +
                "            },\n" +
                "               \"industry\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"search_analyzer\": \"ik_smart\"\n" +
                "            },\n" +
                "               \"registrationStatus\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"search_analyzer\": \"ik_smart\"\n" +
                "            }\n" +
                "        }\n" +
                "}", XContentType.JSON);
        request.settings("{\n" +
                "        \"analysis\":{\n" +
                "            \"analyzer\":{\n" +
                "                \"default\":{\n" +
                "                    \"type\":\"ik_max_word\"\n" +
                "                },\n" +
                "                \"default_search\":{\n" +
                "                    \"type\":\"ik_smart\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }", XContentType.JSON);
        try {
            final CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            log.info("indices.create.ret:{}", JSON.toJSONString(createIndexResponse));
            return createIndexResponse;
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }
        return null;
    }

}
