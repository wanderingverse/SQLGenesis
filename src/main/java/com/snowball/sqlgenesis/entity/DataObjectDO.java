package com.snowball.sqlgenesis.entity;

import lombok.*;

import java.util.List;

/**
 * 数据库表信息实体类
 * @author lihui
 * @date 2025/1/15 15:08
 **/

@Data
public class DataObjectDO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 实际表名
     */
    private String table_name;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 表字段信息列表
     */
    private List<DataObjectOfColumnDO> dataObjectOfColumnDOList;

    /**
     * 主表和关联表字段信息列表（相同名称字段进行去重）
     */
    private List<DataObjectOfJoinTableColumnDO> dataObjectOfJoinTableColumnDOList;
}
