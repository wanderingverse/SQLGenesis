package com.snowball.sqlgenesis.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lihui
 * @date 2025/1/16 14:19
 **/
public class DataBaseUtils {

    /**
     * 将符合下划线命名的表名转换为小驼峰命名规则
     *
     * @param tableName 数据库表名
     * @return 小驼峰命名格式的字符串
     */
    public static String toCamelCase(String tableName) {
        // 如果传入的表名为空或null，返回空字符串
        if (tableName == null || tableName.trim().isEmpty()) {
            return "";
        }

        // 去除前后多余的下划线，并统一转换为小写
        tableName = tableName.trim().toLowerCase().replaceAll("^_+|_+$", "");

        // 拆分表名，按下划线分隔
        String[] words = tableName.split("_");

        // 如果表名只有一个单词，直接返回原表名
        if (words.length == 1) {
            return words[0];
        }

        // 构造小驼峰命名：第一个单词保持小写，后面的单词首字母大写
        StringBuilder camelCaseName = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            // 将后面的单词首字母大写，其余字母小写
            camelCaseName.append(words[i].substring(0, 1).toUpperCase())
                         .append(words[i].substring(1).toLowerCase());
        }
        return camelCaseName.toString();
    }

    /**
     * 将符合下划线命名的表名转换为大驼峰命名规则（PascalCase）
     *
     * @param tableName 数据库表名
     * @return 大驼峰命名格式的字符串
     */
    public static String toPascalCase(String tableName) {
        // 如果传入的表名为空或null，返回空字符串
        if (tableName == null || tableName.trim().isEmpty()) {
            return "";
        }

        // 去除前后多余的下划线，并统一转换为小写
        tableName = tableName.trim().toLowerCase().replaceAll("^_+|_+$", "");

        // 拆分表名，按下划线分隔
        String[] words = tableName.split("_");

        // 如果表名只有一个单词，直接返回首字母大写的单词
        if (words.length == 1) {
            return capitalize(words[0]);
        }

        // 构造大驼峰命名：每个单词首字母大写
        StringBuilder pascalCaseName = new StringBuilder();
        for (String word : words) {
            pascalCaseName.append(capitalize(word));
        }

        return pascalCaseName.toString();
    }


    /**
     * 根据数据库获取到的列类型信息，提取数据的列类型
     * @param columnTypeWithValue varchar(100)
     * @return varchar
     */
    public static String extractTypeByColumnType(String columnTypeWithValue) {
        columnTypeWithValue = columnTypeWithValue.replaceAll("\\s+", "");
        String regex = "(\\w+?)\\((\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(columnTypeWithValue);

        if (matcher.find()) {
            // 提取类型部分 (比如 int, varchar)
            return matcher.group(1);
        }
        // 无法提取，返回原参数
        return columnTypeWithValue;
    }

    /**
     * 将单词的首字母转换为大写，其余字母小写
     *
     * @param word 单词
     * @return 首字母大写的单词
     */
    private static String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
