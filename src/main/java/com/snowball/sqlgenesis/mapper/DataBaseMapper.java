package com.snowball.sqlgenesis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author lihui
 * @date 2025/1/16 12:16
 **/


@Mapper
public interface DataBaseMapper {

    /**
     * 获取表结构信息
     * @param tableName 表名
     * @return 表结构信息
     */
    @Select("show create table ${tableName}")
    Map<String, String> getCreateTableInfo(String tableName);


    /**
     * 获取指定数据表的表注释信息
     * @param tableName 表名
     * @return 表名和表注释
     */
    @Select("select TABLE_NAME,TABLE_COMMENT from information_schema.tables where TABLE_NAME = #{tableName} and TABLE_SCHEMA = DATABASE();")
    Map<String, String> getTableComment(String tableName);

    /**
     * 获取指定数据表、指定列名的单条列注释信息
     * @param tableName 表名
     * @param columnName 列名
     * @return 列注释
     */
    @Select("select COLUMN_COMMENT from information_schema.COLUMNS where TABLE_SCHEMA = database() and TABLE_NAME = #{tableName} and COLUMN_NAME = #{columnName}")
    String getColumnComment(String tableName, String columnName);
}
