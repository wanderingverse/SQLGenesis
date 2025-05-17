package com.snowball.sqlgenesis.service.impl;

import com.snowball.sqlgenesis.common.MysqlToJavaType;
import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.entity.DataObjectOfColumnDO;
import com.snowball.sqlgenesis.entity.DataObjectOfJoinTableColumnDO;
import com.snowball.sqlgenesis.mapper.DataBaseMapper;
import com.snowball.sqlgenesis.service.DataBaseService;
import com.snowball.sqlgenesis.util.DataBaseUtils;
import jakarta.annotation.Resource;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;


/**
 * @author lihui
 * @date 2025/1/16 12:02
 **/

@Service
public class DataBaseServiceImpl implements DataBaseService {

    @Resource
    private DataBaseMapper dataBaseMapper;

    @Override
    public DataObjectDO getTableInfo(String tableName) throws JSQLParserException {
        Map<String, String> createTableInfo = new HashMap<>();
        try {
            createTableInfo = dataBaseMapper.getCreateTableInfo(tableName);
        } catch (Exception e) {
            return null;
        }
        Map<String, String> tableCommentInfo = dataBaseMapper.getTableComment(tableName);
        if (ObjectUtils.isEmpty(tableCommentInfo)) {
            return null;
        }
        String createTable = createTableInfo.get("Create Table");
        String tableComment = tableCommentInfo.get("TABLE_COMMENT");

        CreateTable createTableParse = (CreateTable) CCJSqlParserUtil.parse(createTable);

        DataObjectDO dataObjectDO = new DataObjectDO();
        List<DataObjectOfColumnDO> dataObjectOfColumnDOList = new ArrayList<>();

        dataObjectDO.setTable_name(createTableParse.getTable().getName().replace("`", ""));
        dataObjectDO.setTableName(DataBaseUtils.toPascalCase(dataObjectDO.getTable_name()));
        dataObjectDO.setTableComment(tableComment);

        List<ColumnDefinition> columnDefinitionList = createTableParse.getColumnDefinitions();
        for (ColumnDefinition columnDefinition : columnDefinitionList) {
            DataObjectOfColumnDO dataObjectOfColumnDO = new DataObjectOfColumnDO();
            String columnName = columnDefinition.getColumnName();
            dataObjectOfColumnDO.setColumn_name(columnName.replace("`", ""));
            dataObjectOfColumnDO.setColumnName(DataBaseUtils.toCamelCase(dataObjectOfColumnDO.getColumn_name()));
            String dataType = DataBaseUtils.extractTypeByColumnType(columnDefinition.getColDataType().getDataType());
            dataObjectOfColumnDO.setColumnType(MysqlToJavaType.mapMysqlToJavaType(Objects.requireNonNull(dataType)));
            dataObjectOfColumnDO.setColumnComment(dataBaseMapper.getColumnComment(dataObjectDO.getTable_name(), dataObjectOfColumnDO.getColumn_name()));
            dataObjectOfColumnDOList.add(dataObjectOfColumnDO);
        }
        dataObjectDO.setDataObjectOfColumnDOList(dataObjectOfColumnDOList);
        return dataObjectDO;
    }

    @Override
    public List<DataObjectDO> getAssociationTableInfo(DataObjectDO tableInfo) throws JSQLParserException {
        if (ObjectUtils.isEmpty(tableInfo)) {
            return new ArrayList<>();
        }
        List<DataObjectDO> dataObjectDOList = new ArrayList<>();
        for (DataObjectOfColumnDO dataObjectOfColumnDO : tableInfo.getDataObjectOfColumnDOList()) {
            String columnName = dataObjectOfColumnDO.getColumn_name();
            if (columnName.endsWith("_id")) {
                columnName = columnName.replace("_id", "");
                DataObjectDO dataObjectDO = getTableInfo(columnName);
                if (ObjectUtils.isEmpty(dataObjectDO)) {
                    continue;
                }
                dataObjectDOList.add(dataObjectDO);
            }
        }
        return dataObjectDOList;
    }

    @Override
    public List<DataObjectDO> getTableAndAssociation( String tableName) throws JSQLParserException {
        DataObjectDO tableInfo = getTableInfo(tableName);

        // 封装主表和关联表的所有字段，去重
        Set<DataObjectOfJoinTableColumnDO> dataObjectOfJoinTableColumnSet = new HashSet<>();
        for (DataObjectOfColumnDO dataObjectOfColumnDO : tableInfo.getDataObjectOfColumnDOList()){
            DataObjectOfJoinTableColumnDO dataObjectOfJoinTableColumnDO = new DataObjectOfJoinTableColumnDO();
            BeanUtils.copyProperties(dataObjectOfColumnDO, dataObjectOfJoinTableColumnDO);
            dataObjectOfJoinTableColumnSet.add(dataObjectOfJoinTableColumnDO);
        }
        List<DataObjectDO> dataObjectDOList = getAssociationTableInfo(tableInfo);
        for (DataObjectDO dataObjectDO : dataObjectDOList) {
            for (DataObjectOfColumnDO dataObjectOfColumnDO : dataObjectDO.getDataObjectOfColumnDOList()) {
                DataObjectOfJoinTableColumnDO dataObjectOfJoinTableColumnDO = new DataObjectOfJoinTableColumnDO();
                BeanUtils.copyProperties(dataObjectOfColumnDO, dataObjectOfJoinTableColumnDO);
                dataObjectOfJoinTableColumnSet.add(dataObjectOfJoinTableColumnDO);
            }
        }

        // 转为 List
        List<DataObjectOfJoinTableColumnDO> dataObjectOfJoinTableColumnDOList = new ArrayList<>(dataObjectOfJoinTableColumnSet);
        tableInfo.setDataObjectOfJoinTableColumnDOList(dataObjectOfJoinTableColumnDOList);
        dataObjectDOList.add(tableInfo);

        return dataObjectDOList;
    }
}
