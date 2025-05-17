package com.snowball.sqlgenesis.service.impl;

import com.snowball.sqlgenesis.common.SystemConst;
import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.BusinessLogicService;
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
 * @date 2025/1/16 18:35
 **/

@Slf4j
@Service
public class BusinessLogicImpl implements BusinessLogicService {

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private DataBaseService dataBaseService;

    @Resource
    private EntityGeneratorService entityGeneratorService;

    @Override
    public String generateService(DataObjectDO dataObjectDO) throws IOException {
        if (ObjectUtils.isEmpty(dataObjectDO)){
            return "";
        }
        Context context = new Context();
        context.setVariable("tableName", dataObjectDO.getTableName());
        context.setVariable("table_name", dataObjectDO.getTable_name());
        context.setVariable("dataObjectOfColumnDOList", dataObjectDO.getDataObjectOfColumnDOList());
        String generatedCode = templateEngine.process("thymeleaf/Service", context);
        log.info("generatedCode: {}", generatedCode);
        String savedPath = FileUtils.saveFile(dataObjectDO.getTableName() + "Service",
                                              "java",
                                              SystemConst.RESOURCE_PATH + "/service/",
                                              generatedCode);
        log.info("File saved successfully. File path: {}", savedPath);
        return savedPath;
    }

    @Override
    public String generateServiceImpl(DataObjectDO dataObjectDO) throws IOException {
        if (ObjectUtils.isEmpty(dataObjectDO)){
            return "";
        }
        Context context = new Context();
        context.setVariable("tableName", dataObjectDO.getTableName());
        String generatedCode = templateEngine.process("thymeleaf/ServiceImpl", context);
        log.info("generatedCode: {}", generatedCode);
        String savedPath = FileUtils.saveFile(dataObjectDO.getTableName() + "ServiceImpl",
                                              "java",
                                              SystemConst.RESOURCE_PATH + "/service/impl/",
                                              generatedCode);
        log.info("File saved successfully. File path: {}", savedPath);
        return savedPath;
    }

    @Override
    public String generateServiceByTableName(String tableName) throws JSQLParserException, IOException {
        DataObjectDO dataObjectDO = dataBaseService.getTableInfo(tableName);
        return generateService(dataObjectDO);
    }

    @Override
    public String generateServiceImplByTableName(String tableName) throws JSQLParserException, IOException {
        DataObjectDO dataObjectDO = dataBaseService.getTableInfo(tableName);
        return generateServiceImpl(dataObjectDO);
    }

    @Override
    public String generateServiceAndServiceImplByTableName(String tableName) throws JSQLParserException, IOException {
        DataObjectDO dataObjectDO = dataBaseService.getTableInfo(tableName);
        String generateServicePath = generateService(dataObjectDO);
        String generateServiceImplPath = generateServiceImpl(dataObjectDO);
        String generateRequestDtoPath = entityGeneratorService.generateRequestDTO(dataObjectDO);
        String generateResponseDtoPath = entityGeneratorService.generateResponseDTO(dataObjectDO);
        return generateServicePath + "，" + generateServiceImplPath + "，" + generateRequestDtoPath + "，" + generateResponseDtoPath;
    }
}
