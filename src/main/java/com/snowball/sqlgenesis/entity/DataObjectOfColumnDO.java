package com.snowball.sqlgenesis.entity;

import lombok.Data;

/**
 * 数据库表中列信息实体类
 * @author lihui
 * @date 2025/1/15 15:17
 **/
@Data
public class DataObjectOfColumnDO {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 实际列名
     */
    private String column_name;

    /**
     * 列注释
     */
    private String columnComment;

    /**
     * 列类型
     */
    private String columnType;
}
