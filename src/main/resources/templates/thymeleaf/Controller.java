@Validated
@RestController
@RequestMapping("/[(${table_name})]")
public class [(${tableName})]Controller {

    @Resource
    private [(${tableName})]Service [(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Service;

    /**
     * 条件查询 [(${table_name})] 列表，支持分页
     * @param pageNum 页码
     * @param pageSize 每页数量
     */
    @GetMapping("/list")
    public ResponseUtil<IPage<[(${tableName})]ResponseDTO>> get[(${tableName})]List(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            [# th:each="column,iterStat : ${dataObjectOfJoinTableColumnDOList}"]
            @RequestParam(required = false) [(${column.columnType})] [(${column.columnName})][(${!iterStat.last ? "," : ""})][/]) {
        return ResponseUtil.success([(${tableName.substring(0,1).toLowerCase() + tableName.substring(1)})]Service.get[(${tableName})]List(pageNum, pageSize));
    }

    /**
     * 根据主键 ID 查询 [(${table_name})] 表中单条数据
     * @param id [(${table_name})] 表主键 ID
     */
    @GetMapping("/by_id")
    public ResponseUtil<[(${tableName})]DO> get[(${tableName})]ById(@RequestParam(required = true) Long id) {
        return ResponseUtil.success([(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Service.get[(${tableName})]ById(id));
    }

    /**
     * 根据 ID 删除 [(${table_name})] 表中相关数据
     * @param id [(${table_name})] 表主键 ID
     */
    @DeleteMapping("/by_id")
    public ResponseUtil<Void> delete[(${tableName})]ById(@RequestParam(required = true) Long id) {
        [(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Service.delete[(${tableName})]ById(id);
        return ResponseUtil.success();
    }
}