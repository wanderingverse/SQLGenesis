package com.snowball.sqlgenesis.controller;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.DataBaseService;
import com.snowball.sqlgenesis.util.ResponseUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据库字段获取和处理接口
 *
 * @author lihui
 * @date 2025/1/15 18:13
 **/

@Slf4j
@RestController
@RequestMapping("/database")
public class DataBaseController {

    @Resource
    private DataBaseService dataBaseService;

    /**
     * 获取 mysql 中指定表的相关信息
     *
     * @param tableName 表名
     * @return 表信息
     */
    @GetMapping("/table_info")
    public ResponseUtil<DataObjectDO> getTableInfo(@RequestParam(required = true) String tableName) throws JSQLParserException {
        DataObjectDO tableInfo = dataBaseService.getTableInfo(tableName);
        return ResponseUtil.success(tableInfo);
    }

    /**
     * 获取指定表的关联表信息
     */
    @GetMapping("/association_table_info")
    public ResponseUtil<List<DataObjectDO>> getAssociationTableInfo(@RequestBody DataObjectDO tableInfo) throws JSQLParserException {
        return ResponseUtil.success(dataBaseService.getAssociationTableInfo(tableInfo));
    }

    /**
     * 根据表名获取指定表及其关联表的信息
     */
    @GetMapping("/table_and_association")
    public ResponseUtil<List<DataObjectDO>> getTableAndAssociation(@RequestParam(required = true) String tableName) throws JSQLParserException {
        return ResponseUtil.success(dataBaseService.getTableAndAssociation(tableName));
    }
}
