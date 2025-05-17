package com.snowball.sqlgenesis.service;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import net.sf.jsqlparser.JSQLParserException;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/16 18:35
 **/
public interface BusinessLogicService {

    /**
     * 根据表结构生成 Service 层接口
     *
     * @param dataObjectDO 表结构信息
     * @return Service 文件路径
     */
    String generateService(DataObjectDO dataObjectDO) throws IOException;


    /**
     * 根据表结构生成 Service 层实现类
     *
     * @param dataObjectDO 表结构信息
     * @return ServiceImpl 文件路径
     */
    String generateServiceImpl(DataObjectDO dataObjectDO) throws IOException;


    /**
     * 根据表名生成 Service 层接口
     * @param tableName 表名
     * @return Service 文件路径
     */
    String generateServiceByTableName(String tableName) throws JSQLParserException, IOException;


    /**
     * 根据表名生成 Service 层实现类
     * @param tableName 表名
     * @return ServiceImpl 文件路径
     */
    String generateServiceImplByTableName(String tableName) throws JSQLParserException, IOException;


    /**
     * 根据表名生成 Service 层接口、Service实现类、RequestDTO、ResponseDTO
     * @param tableName 表名
     * @return Service 和 ServiceImpl 文件路径
     */
    String generateServiceAndServiceImplByTableName(String tableName) throws JSQLParserException, IOException;
}
