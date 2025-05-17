@Service
public class [(${tableName})]ServiceImpl implements [(${tableName})]Service {

    @Resource
    private [(${tableName})]Mapper [(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Mapper;

    @Override
    public IPage<[(${tableName})]ResponseDTO> get[(${tableName})]List(int pageNum, int pageSize) {
        MPJLambdaWrapper<[(${tableName})]DO> wrapper = new MPJLambdaWrapper<[(${tableName})]DO>()
        .selectAll([(${tableName})]DO.class);
        Page<[(${tableName})]ResponseDTO> [(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Page =[(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Mapper.selectJoinPage(new Page<>(pageNum, pageSize), [(${tableName})]ResponseDTO.class,wrapper);
        return [(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Page;
    }

    @Override
    public [(${tableName})]DO get[(${tableName})]ById(Long id) {
        return [(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Mapper.selectById(id);
    }

    @Override
    public void delete[(${tableName})]ById(Long id) {
        [(${tableName.substring(0,1).toLowerCase()+tableName.substring(1)})]Mapper.deleteById(id);
    }
}