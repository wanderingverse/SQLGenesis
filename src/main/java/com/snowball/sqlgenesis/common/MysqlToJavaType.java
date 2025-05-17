package com.snowball.sqlgenesis.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihui
 * @date 2025/1/16 14:42
 **/
@Getter
public enum MysqlToJavaType {

    /**
     * 数据库 int 类型 映射到 java 类型 Integer
     */
    INT("int", "Integer"),
    TINYINT("tinyint", "Byte"),
    SMALLINT("smallint", "Short"),
    MEDIUMINT("mediumint", "Integer"),
    BIGINT("bigint", "Long"),
    FLOAT("float", "Float"),
    DOUBLE("double", "Double"),
    DECIMAL("decimal", "BigDecimal"),
    CHAR("char", "String"),
    VARCHAR("varchar", "String"),
    TEXT("text", "String"),
    DATE("date", "java.sql.Date"),
    DATETIME("datetime", "LocalDateTime"),
    TIMESTAMP("timestamp", "LocalDateTime"),
    TIME("time", "LocalDate"),
    BLOB("blob", "byte[]"),
    CLOB("clob", "String"),
    JSON("json", "String");

    private final String mysqlType;
    private final String javaType;


    MysqlToJavaType(String mysqlType, String javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

    /**
     * 创建一个Map 用于快速查找
     */
    private static final Map<String, MysqlToJavaType> FIND_MAP = new HashMap<>();

    static {
        for (MysqlToJavaType type : MysqlToJavaType.values()) {
            FIND_MAP.put(type.mysqlType.toLowerCase(), type);
        }
    }


    /**
     * 根据 MySQL 类型返回 Java 类型
     * @param mysqlType MySQL 类型
     * @return Java 类型
     */
    public static String mapMysqlToJavaType(String mysqlType) {
        MysqlToJavaType type = FIND_MAP.get(mysqlType.toLowerCase());
        return type != null ? type.javaType : "Object";
    }
}
