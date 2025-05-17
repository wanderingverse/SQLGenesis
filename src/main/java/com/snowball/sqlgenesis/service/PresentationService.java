package com.snowball.sqlgenesis.service;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import net.sf.jsqlparser.JSQLParserException;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/17 9:53
 **/
public interface PresentationService {

    /**
     * 根据表结构生成 Controller 层接口
     * @param dataObjectDO 表结构信息
     * @return Controller 层文件路径
     * @throws IOException IOException
     */
    String generateController(DataObjectDO dataObjectDO) throws IOException;

    /**
     * 根据表名生成单表的 Controller 层接口，完整的三层架构
     * @param tableName 表名
     * @return Controller 层文件路径
     * @throws JSQLParserException JSQLParserException
     * @throws IOException IOException
     */
    String generateControllerByTableName(String tableName) throws JSQLParserException, IOException;

    /**
     * 根据表名生成单表及其关联的 Controller 层接口
     * <p>包含主表及其关联表的三层架构完整代码：Controller、Service、ServiceImpl、Mapper、实体类
     * @param tableName 表名
     * @return Controller 层文件路径
     * @throws JSQLParserException JSQLParserException
     * @throws IOException IOException
     */
    String generateControllerByTableAndAssociation(String tableName) throws JSQLParserException, IOException;
}
