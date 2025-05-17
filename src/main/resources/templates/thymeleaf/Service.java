public interface [(${tableName})]Service {

    /**
     * 条件查询 [(${table_name})] 列表，支持分页
     * @param pageNum 页码
     * @param pageSize 每页数量
     */
    public IPage<[(${tableName})]ResponseDTO> get[(${tableName})]List(int pageNum, int pageSize);

    /**
     * 根据主键 ID 查询 [(${table_name})] 表中单条数据
     * @param id [(${table_name})] 表主键 ID
     */
    public [(${tableName})]DO get[(${tableName})]ById(Long id);

    /**
     * 根据 ID 删除 [(${table_name})] 表中相关数据
     * @param id [(${table_name})] 表主键 ID
     */
    public void delete[(${tableName})]ById(Long id);
}