package com.snowball.sqlgenesis.controller;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.PresentationService;
import com.snowball.sqlgenesis.util.ResponseUtil;
import jakarta.annotation.Resource;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/17 9:47
 **/

@Validated
@RestController
@RequestMapping("/presentation")
public class PresentationController {

    @Resource
    private PresentationService presentationService;


    /**
     * 根据表结构生成 Controller 层接口
     */
    @PostMapping("/controller")
    public ResponseUtil<String> generateController(@RequestBody DataObjectDO dataObjectDO) throws IOException {
        String filePath = presentationService.generateController(dataObjectDO);
        return ResponseUtil.success(filePath);
    }

    /**
     * 根据表名生成单表的 Controller 层接口
     * <p>包含三层架构完整代码：Controller、Service、ServiceImpl、Mapper、实体类
     */
    @PostMapping("/controller_by_table_name")
    public ResponseUtil<String> generateControllerByTableName(@RequestParam(required = true) @NotBlank String tableName) throws IOException, JSQLParserException {
        String filePath = presentationService.generateControllerByTableName(tableName);
        return ResponseUtil.success(filePath);
    }

    /**
     * 根据表名生成全表的 Controller 层接口
     * <p>包含主表及其关联表的三层架构完整代码：Controller、Service、ServiceImpl、Mapper、实体类
     */
    @PostMapping("/controller_by_table_and_association")
    public ResponseUtil<String> generateControllerByTableAndAssociation(@RequestParam(required = true) @NotBlank String tableName) throws IOException, JSQLParserException {
        String filePath = presentationService.generateControllerByTableAndAssociation(tableName);
        return ResponseUtil.success(filePath);
    }
}
