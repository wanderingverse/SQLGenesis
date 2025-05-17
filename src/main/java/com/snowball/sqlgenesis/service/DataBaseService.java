package com.snowball.sqlgenesis.service;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import net.sf.jsqlparser.JSQLParserException;

import java.util.List;

/**
 * @author lihui
 * @date 2025/1/16 12:02
 **/
public interface DataBaseService {

    /**
     * 获取表信息
     *
     * @param tableName 表名
     * @return 表信息
     * @throws JSQLParserException JSQLParserException
     */
    DataObjectDO getTableInfo(String tableName) throws JSQLParserException;

    /**
     * 获取指定表的相关表信息，存储为列表
     *
     * @param tableInfo 表信息对象
     * @return 表信息列表
     */
    List<DataObjectDO> getAssociationTableInfo(DataObjectDO tableInfo) throws JSQLParserException;

    /**
     * 根据表名获取指定表及其关联表的信息
     * @param tableName 表信息对象
     * @return 所有表信息列表
     * @throws JSQLParserException JSQLParserException
     */
    List<DataObjectDO> getTableAndAssociation( String tableName) throws JSQLParserException;
}
