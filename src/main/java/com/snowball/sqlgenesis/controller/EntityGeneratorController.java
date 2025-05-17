package com.snowball.sqlgenesis.controller;

import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.EntityGeneratorService;
import com.snowball.sqlgenesis.util.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 实体类生成器接口
 *
 * @author lihui
 * @date 2025/1/15 15:03
 **/

@Validated
@RestController
@RequestMapping("/entity_generator")
public class EntityGeneratorController {

    @Resource
    private EntityGeneratorService entityGeneratorService;

    /**
     * 根据表结构信息，生成数据库表对应的实体类
     */
    @PostMapping("/do")
    public ResponseUtil<String> generateDO(@RequestBody DataObjectDO dataObjectDO) throws IOException {
        String filePath = entityGeneratorService.generateDO(dataObjectDO);
        return ResponseUtil.success(filePath);
    }

    /**
     * 根据表名，生成数据库表对应的实体类
     */
    @PostMapping("/do_by_table_name")
    public ResponseUtil<String> generateByTableName(@RequestParam(required = true) @NotBlank String tableName) throws IOException, JSQLParserException {
        String filePath = entityGeneratorService.generateByTableName(tableName);
        return ResponseUtil.success(filePath);
    }

    /**
     * 根据表结构信息，生成数据库表对应的 ResponseDTO 类
     */
    @PostMapping("/response_dto")
    public ResponseUtil<String> generateResponseDTO(@RequestBody DataObjectDO dataObjectDO) throws IOException {
        String filePath = entityGeneratorService.generateResponseDTO(dataObjectDO);
        return ResponseUtil.success(filePath);
    }

    /**
     * 根据表结构信息，生成数据库表对应的 RequestDTO 类
     */
    @PostMapping("/request_dto")
    public ResponseUtil<String> generateRequestDTO(@RequestBody DataObjectDO dataObjectDO) throws IOException {
        String filePath = entityGeneratorService.generateRequestDTO(dataObjectDO);
        return ResponseUtil.success(filePath);
    }
}