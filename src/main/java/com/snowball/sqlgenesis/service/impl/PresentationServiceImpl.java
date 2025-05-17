package com.snowball.sqlgenesis.service.impl;

import com.snowball.sqlgenesis.common.SystemConst;
import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.*;
import com.snowball.sqlgenesis.util.FileUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author lihui
 * @date 2025/1/17 9:53
 **/
@Slf4j
@Service
public class PresentationServiceImpl implements PresentationService {

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private DataBaseService dataBaseService;

    @Resource
    private BusinessLogicService businessLogicService;

    @Resource
    private EntityGeneratorService entityGeneratorService;

    @Resource
    private DataPersistenceService dataPersistenceService;

    @Override
    public String generateController(DataObjectDO dataObjectDO) throws IOException {
        if (ObjectUtils.isEmpty(dataObjectDO)){
            return "";
        }
        Context context = new Context();
        context.setVariable("tableName", dataObjectDO.getTableName());
        context.setVariable("table_name", dataObjectDO.getTable_name());
        context.setVariable("dataObjectOfColumnDOList", dataObjectDO.getDataObjectOfColumnDOList());
        context.setVariable("dataObjectOfJoinTableColumnDOList",dataObjectDO.getDataObjectOfJoinTableColumnDOList());
        String generatedCode = templateEngine.process("thymeleaf/Controller", context);
        log.info("generatedCode: {}", generatedCode);
        String savedPath = FileUtils.saveFile(dataObjectDO.getTableName() + "Controller",
                                              "java", SystemConst.RESOURCE_PATH + "/controller/", generatedCode);
        log.info("File saved successfully. File path: {}", savedPath);
        return savedPath;
    }

    @Override
    public String generateControllerByTableName(String tableName) throws JSQLParserException, IOException {
        DataObjectDO tableInfo = dataBaseService.getTableInfo(tableName);
        String generateControllerPath = generateController(tableInfo);
        String generateServicePath = businessLogicService.generateService(tableInfo);
        String generateServiceImplPath = businessLogicService.generateServiceImpl(tableInfo);
        String generateRequestDtoPath = entityGeneratorService.generateRequestDTO(tableInfo);
        String generateResponseDtoPath = entityGeneratorService.generateResponseDTO(tableInfo);
        String generateDataPersistencePath = dataPersistenceService.generateDataPersistence(tableInfo);
        String generateDoPath = entityGeneratorService.generateDO(tableInfo);
        return generateControllerPath + "，" + generateServicePath + "，" + generateServiceImplPath + "，" + generateRequestDtoPath + "，" + generateResponseDtoPath + "，" + generateDataPersistencePath + "，" + generateDoPath;
    }

    @Override
    public String generateControllerByTableAndAssociation(String tableName) throws JSQLParserException, IOException {
        List<DataObjectDO> tableAndAssociationList = dataBaseService.getTableAndAssociation(tableName);
        StringBuilder stringBuilder = new StringBuilder();
        for (DataObjectDO dataObjectDO : tableAndAssociationList) {
            if (ObjectUtils.isEmpty(dataObjectDO)){
                continue;
            }
            stringBuilder.append(generateControllerByTableName(dataObjectDO.getTable_name()));
        }
        return stringBuilder.toString();
    }
}
