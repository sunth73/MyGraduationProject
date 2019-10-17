package com.wmk.porject.mapper.validatemapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-12 10:45
 * 描述
 */
public interface ValidateMapper {
    /**
     * 字段查重接口
     * @param columnName
     * @param tableName
     * @param dateValue
     * @param tableIdName
     * @param tableIdValue
     * @return
     */
    List<String> duplicateChecking(@Param("columnName")String columnName, @Param("tableName")String tableName, @Param("dateValue")String dateValue
            , @Param("tableIdName")String tableIdName, @Param("tableIdValue")String tableIdValue);
}
