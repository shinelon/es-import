package com.shinelon.esimport.service.impl;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.shinelon.esimport.bean.BossCompanyImport;
import com.shinelon.esimport.service.ICompanyIndexOptService;
import com.shinelon.esimport.service.ICsvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CsvServiceImpl
 * @Author shinelon
 * @Date 13:52 2022/5/30
 * @Version 1.0
 **/
@Service
@Slf4j
public class CsvServiceImpl implements ICsvService {

    @Value("${csv.path}")
    private String csvPath;
    @Autowired
    private ICompanyIndexOptService companyIndexOptService;

    @Override
    public void checkFile(){
        log.info("csv.path:{}",csvPath);
        File file = FileUtil.file(csvPath);
        if(!file.exists()){
            throw new RuntimeException("file is not exists");
        }
    }

    @Override
    public void readFile() {
        File file = FileUtil.file(csvPath);
        long startTime = System.currentTimeMillis();
        EasyExcel.read(file)
                .excelType(ExcelTypeEnum.CSV)
                .headRowNumber(0)
                .head(BossCompanyImport.class)
                .registerReadListener(new AnalysisEventListener<BossCompanyImport>() {
                    public static final int BATCH_COUNT = 10000;
                    private AtomicInteger times = new AtomicInteger(0);
                    private List<BossCompanyImport> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

                    @Override
                    public void invoke(BossCompanyImport data, AnalysisContext context) {
                        cachedDataList.add(data);
                        if (cachedDataList.size() >= BATCH_COUNT) {
                            saveData();
                            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                        }
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        log.info("doAfterAllAnalysed");
                        saveData();
                    }

                    private void saveData() {
                        long tmpEndTime = System.currentTimeMillis();
                        int size = cachedDataList.size();
                        log.info("{}条数据，开始存储数据库！", size);
                        companyIndexOptService.bulkAdd(cachedDataList);
                        log.info("第{}次 存储数据库成功！{}条，{}秒", times.addAndGet(1),size,(tmpEndTime - startTime) / 1000);
                    }

                })
                .charset(Charset.forName("UTF-8"))
                .sheet()
                .doRead();
        long endTime = System.currentTimeMillis();
        log.info("readFileDone:{}s", (endTime - startTime) / 1000);
    }
}
