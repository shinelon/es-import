package com.shinelon.esimport.service.impl;

import com.shinelon.esimport.service.ICompanyIndexOptService;
import com.shinelon.esimport.service.ICsvService;
import com.shinelon.esimport.service.IMigrationService;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MigrationServiceImpl
 * @Author shinelon
 * @Date 20:10 2022/5/30
 * @Version 1.0
 **/
@Service
public class MigrationServiceImpl implements IMigrationService {

    @Autowired
    private ICsvService csvService;
    @Autowired
    private ICompanyIndexOptService companyIndexOptService;

    @Override
    public void modeCreate(){
        companyIndexOptService.createCompanyIndex();
    }

    @Override
    public void modeReset(){
        AcknowledgedResponse acknowledgedResponse = companyIndexOptService.delCompanyIndex();
        if(acknowledgedResponse.isAcknowledged()){
            sleepForWhile(5000);
            companyIndexOptService.createCompanyIndex();
        }

    }

    @Override
    public void migration(){
        csvService.readFile();
    }

    private void sleepForWhile(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
