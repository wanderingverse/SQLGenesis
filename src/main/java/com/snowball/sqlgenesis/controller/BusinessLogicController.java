package com.snowball.sqlgenesis.controller;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.BusinessLogicService;
import com.snowball.sqlgenesis.util.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/16 18:35
 **/

@Validated
@RestController
@RequestMapping("/business_logic")
public class BusinessLogicController {

    @Resource
    private BusinessLogicService businessLogicService;

    /**
     * 根据表结构生成 Service 层接口
     */
    @PostMapping("/service")
    public ResponseUtil<String> generateService(@RequestBody DataObjectDO dataObjectDO) throws IOException {
        return ResponseUtil.success(businessLogicService.generateService(dataObjectDO));
    }

    /**
     * 根据表结构生成 Service 层实现类
     */
    @PostMapping("/service_impl")
    public ResponseUtil<String> generateServiceImpl(@RequestBody DataObjectDO dataObjectDO) throws IOException {
        return ResponseUtil.success(businessLogicService.generateServiceImpl(dataObjectDO));
    }

    /**
     * 根据表名生成 Service 层接口
     */
    @PostMapping("/service_by_table_name")
    public ResponseUtil<String> generateServiceByTableName(@RequestParam(required = true) @NotBlank String tableName) throws JSQLParserException, IOException {
        return ResponseUtil.success(businessLogicService.generateServiceByTableName(tableName));
    }

    /**
     * 根据表名生成 Service 层实现类
     */
    @PostMapping("/service_impl_by_table_name")
    public ResponseUtil<String> generateServiceImplByTableName(@RequestParam(required = true) @NotBlank String tableName) throws JSQLParserException, IOException {
        return ResponseUtil.success(businessLogicService.generateServiceImplByTableName(tableName));
    }

    /**
     * 根据表名生成 Service 层接口、Service实现类、RequestDTO、ResponseDTO
     */
    @PostMapping("/service_and_service_impl_by_table_name")
    public ResponseUtil<String> generateServiceAndServiceImplByTableName(@RequestParam(required = true) @NotBlank String tableName) throws JSQLParserException, IOException {
        return ResponseUtil.success(businessLogicService.generateServiceAndServiceImplByTableName(tableName));
    }
}
