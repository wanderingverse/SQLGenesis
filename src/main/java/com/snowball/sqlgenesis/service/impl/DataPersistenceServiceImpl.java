package com.snowball.sqlgenesis.service.impl;

import com.snowball.sqlgenesis.common.SystemConst;
import com.snowball.sqlgenesis.entity.DataObjectDO;
import com.snowball.sqlgenesis.service.DataBaseService;
import com.snowball.sqlgenesis.service.DataPersistenceService;
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
 * @date 2025/1/16 17:52
 **/


@Slf4j
@Service
public class DataPersistenceServiceImpl implements DataPersistenceService {

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private DataBaseService dataBaseService;

    @Resource
    private EntityGeneratorService entityGeneratorService;


    @Override
    public String generateDataPersistence(DataObjectDO dataObjectDO) throws IOException {
        if (ObjectUtils.isEmpty(dataObjectDO)){
            return "";
        }
        Context context = new Context();
        context.setVariable("tableName", dataObjectDO.getTableName());
        String generatedCode = templateEngine.process("thymeleaf/Mapper", context);
        log.info("generatedCode: {}", generatedCode);
        String savedPath = FileUtils.saveFile(dataObjectDO.getTableName() + "Mapper",
                                              "java",
                                              SystemConst.RESOURCE_PATH + "/mapper/",
                                              generatedCode);
        log.info("File saved successfully. File path: {}", savedPath);
        return savedPath;
    }

    @Override
    public String generateDataPersistenceByTableName(String tableName) throws JSQLParserException, IOException {
        DataObjectDO dataObjectDO = dataBaseService.getTableInfo(tableName);
        String generateDataPersistencePath = generateDataPersistence(dataObjectDO);
        String generateDoPath = entityGeneratorService.generateDO(dataObjectDO);
        return generateDataPersistencePath + "，" + generateDoPath;
    }
}
