package com.shinelon.esimport.service;

import com.shinelon.esimport.bean.BossCompanyImport;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.util.List;

/**
 * @author Shinelon
 * @date 2022-05-05 10:50
 */
public interface ICompanyIndexOptService {


    /**
     * 创吉公司索引
     *
     * @return
     */
    CreateIndexResponse createCompanyIndex();


    /**
     * delCompanyIndex
     *
     * @return {@link AcknowledgedResponse}
     */
    AcknowledgedResponse delCompanyIndex();


    /**
     * bulkAdd
     *
     * @param list
     * @return
     */
    BulkResponse bulkAdd(List<BossCompanyImport> list);
}
