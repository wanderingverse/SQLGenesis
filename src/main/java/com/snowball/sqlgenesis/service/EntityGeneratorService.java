package com.snowball.sqlgenesis.service;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import net.sf.jsqlparser.JSQLParserException;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/16 12:03
 **/
public interface EntityGeneratorService {

    /**
     * 根据表结构信息，生成数据库表对应的实体类 DO
     * @param dataObjectDO 表结构信息
     * @return 实体类保存路径
     * @throws IOException IO异常
     */
    String generateDO(DataObjectDO dataObjectDO) throws IOException;

    /**
     * 根据表名，生成数据库表对应的实体类 DO
     * @param tableName 表名
     * @return 实体类保存路径
     * @throws JSQLParserException SQL解析异常
     * @throws IOException IO异常
     */
    String generateByTableName(String tableName) throws JSQLParserException, IOException;


    /**
     * 根据表结构信息，生成数据库表对应的 ResponseDTO 类
     */
    String generateResponseDTO(DataObjectDO dataObjectDO) throws IOException;


    /**
     * 根据表结构信息，生成数据库表对应的 RequestDTO 类
     */
    String generateRequestDTO(DataObjectDO dataObjectDO) throws IOException;
}
