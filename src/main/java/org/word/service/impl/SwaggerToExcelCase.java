package org.word.service.impl;

import cn.hutool.core.date.DateTime;
import com.testpro.easyrest.Util.ExcelUtil;
import com.testpro.easyrest.Util.JsonUtil;
import com.testpro.easyrest.bean.ExecutionData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.word.model.Table;
import org.word.service.WordService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Slf4j
@Service
public class SwaggerToExcelCase {

    @Autowired
    private WordService tableService;

    public void exec(String filepath, String url) {
        if (filepath == null) {
            filepath = "H:\\";
        }
        Map<String, Object> stringObjectMap = null;
        try {
            stringObjectMap=tableService.tableList(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (stringObjectMap != null) {
            LinkedHashMap<String, String> info = (LinkedHashMap<String, String>) stringObjectMap.get("info");
            String fileName = filepath + info.get("title") + "-" + info.get("version") + UUID.randomUUID() + ".xlsx";

            ArrayList<Table> table = (ArrayList<Table>) stringObjectMap.get("tables");
            doAnalysisCase(fileName, table);
        }

    }

    /**
     *
     * @param fileName 文件存储路径
     * @param tables 接口信息
     */
    void doAnalysisCase(String fileName, ArrayList<Table> tables) {

        ArrayList<ExecutionData> execList= new ArrayList<>();
        for (Table table: tables){
            ExecutionData  exec=new ExecutionData();
            exec.setCaseName(table.getTag()+"_"+table.getDescription());
            exec.setMethod(table.getRequestType().toUpperCase());
            exec.setCaseDescription(table.getDescription());
            exec.setUrl(table.getUrl()==null?"null":table.getUrl());
            exec.setParameters(table.getRequestParam()==null?"":table.getRequestParam());
            execList.add(exec);
        }
        ExcelUtil excelUtil= new ExcelUtil();
        File file = new File(fileName);
        try {
            excelUtil.WriterExcelWithListBean(file,execList,ExecutionData.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
