package com.snowball.sqlgenesis.service.impl;

import com.snowball.sqlgenesis.common.SystemConst;
import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.DataBaseService;
import com.snowball.sqlgenesis.service.EntityGeneratorService;
import com.snowball.sqlgenesis.util.FileUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

/**
 * @author lihui
 * @date 2025/1/16 12:03
 **/

@Slf4j
@Service
public class EntityGeneratorServiceImpl implements EntityGeneratorService {

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private DataBaseService dataBaseService;

    @Override
    public String generateDO(DataObjectDO dataObjectDO) throws IOException {
        if (ObjectUtils.isEmpty(dataObjectDO)){
            return "";
        }
        Context context = new Context();
        context.setVariable("tableName", dataObjectDO.getTableName());
        context.setVariable("table_name", dataObjectDO.getTable_name());
        context.setVariable("tableComment", dataObjectDO.getTableComment());
        context.setVariable("dataObjectOfColumnDOList", dataObjectDO.getDataObjectOfColumnDOList());
        String generatedCode = templateEngine.process("thymeleaf/DO", context);
        log.info("generatedCode: {}", generatedCode);
        String savedPath = FileUtils.saveFile(dataObjectDO.getTableName() + "DO",
                                              "java", SystemConst.RESOURCE_PATH + "/entity/", generatedCode);
        log.info("File saved successfully. File path: {}", savedPath);
        return savedPath;
    }

    @Override
    public String generateByTableName(String tableName) throws JSQLParserException, IOException {
        DataObjectDO tableInfo = dataBaseService.getTableInfo(tableName);
        return generateDO(tableInfo);
    }

    @Override
    public String generateResponseDTO(DataObjectDO dataObjectDO) throws IOException {
        if (ObjectUtils.isEmpty(dataObjectDO)){
            return "";
        }
        Context context = new Context();
        context.setVariable("tableName", dataObjectDO.getTableName());
        String generatedCode = templateEngine.process("thymeleaf/ResponseDTO", context);
        log.info("generatedCode: {}", generatedCode);
        String savedPath = FileUtils.saveFile(dataObjectDO.getTableName() + "ResponseDTO",
                                              "java", SystemConst.RESOURCE_PATH + "/entity/dto/response", generatedCode);
        log.info("File saved successfully. File path: {}", savedPath);
        return savedPath;
    }

    @Override
    public String generateRequestDTO(DataObjectDO dataObjectDO) throws IOException {
        if (ObjectUtils.isEmpty(dataObjectDO)){
            return "";
        }
        Context context = new Context();
        context.setVariable("tableName", dataObjectDO.getTableName());
        String generatedCode = templateEngine.process("thymeleaf/RequestDTO", context);
        log.info("generatedCode: {}", generatedCode);
        String savedPath = FileUtils.saveFile(dataObjectDO.getTableName() + "RequestDTO",
                                              "java", SystemConst.RESOURCE_PATH + "/entity/dto/request", generatedCode);
        log.info("File saved successfully. File path: {}", savedPath);
        return savedPath;
    }
}
