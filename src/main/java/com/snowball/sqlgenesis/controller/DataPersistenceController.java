package com.snowball.sqlgenesis.controller;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.DataPersistenceService;
import com.snowball.sqlgenesis.util.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/16 17:52
 **/

@Validated
@RestController
@RequestMapping("/data_persistence")
public class DataPersistenceController {

    @Resource
    private DataPersistenceService dataPersistenceService;


    /**
     * 根据表结构生成数据持久层接口 Mapper
     */
    @PostMapping("/mapper")
    public ResponseUtil<String> generateDataPersistence(@RequestBody DataObjectDO dataObjectDO) throws IOException {
        return ResponseUtil.success(dataPersistenceService.generateDataPersistence(dataObjectDO));
    }

    /**
     * 根据表名生成数据持久层接口 Mapper、DO类
     */
    @PostMapping("/mapper_by_table_name")
    public ResponseUtil<String> generateDataPersistenceByTableName(@RequestParam(required = true) @NotBlank String tableName) throws IOException, JSQLParserException {
        String filePath = dataPersistenceService.generateDataPersistenceByTableName(tableName);
        return ResponseUtil.success(filePath);
    }
}
