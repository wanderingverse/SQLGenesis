package com.snowball.sqlgenesis.entity;

import lombok.Data;

/**
 * 主表及连表中所有列信息实体类
 * @author lihui
 * @date 2025/1/22 11:42
 **/

@Data
public class DataObjectOfJoinTableColumnDO {

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
