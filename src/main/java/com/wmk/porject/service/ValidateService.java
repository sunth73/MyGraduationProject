package com.wmk.porject.service;

import com.wmk.porject.mapper.validatemapper.ValidateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-12 10:51
 * 描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValidateService {
    @Autowired
    private ValidateMapper validateMapper;

    /**
     * 字段查重
     * @param columnName
     * @param tableName
     * @param dateValue
     * @param tableIdName
     * @param tableIdValue
     * @return
     */
    public boolean duplicateChecking(String columnName,String tableName,String dateValue,String tableIdName,String tableIdValue){
        List<String> list=validateMapper.duplicateChecking(columnName, tableName, dateValue, tableIdName, tableIdValue);
        if(CollectionUtils.isEmpty(list)) {
            return true;
        }else {
            return false;
        }
    }
}
