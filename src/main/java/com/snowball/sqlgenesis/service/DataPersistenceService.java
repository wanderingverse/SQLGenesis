package com.snowball.sqlgenesis.service;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/16 17:52
 **/
public interface DataPersistenceService {

    /**
     * 根据表结构生成数据持久层接口 Mapper
     *
     * @param dataObjectDO 表结构信息
     * @return 生成的 Mapper 文件路径
     */
    String generateDataPersistence(DataObjectDO dataObjectDO) throws IOException;


    /**
     * 根据表名生成数据持久层接口 Mapper、DO类
     *
     * @param tableName 表名
     * @return 生成的 Mapper、DO类 文件路径
     * @throws JSQLParserException SQL解析异常
     * @throws IOException         IO异常
     */
    String generateDataPersistenceByTableName(String tableName) throws JSQLParserException, IOException;
}
