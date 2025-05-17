/**
 * [(${tableComment})]
 */
@Data
@TableName("[(${table_name})]")
public class [(${tableName})]DO {
    [# th:each="column : ${dataObjectOfColumnDOList}"]
    /**
     * [(${column.columnComment})]
     */
    @TableField("[(${column.column_name})]")
    private [(${column.columnType})] [(${column.columnName})];

    [/]
}