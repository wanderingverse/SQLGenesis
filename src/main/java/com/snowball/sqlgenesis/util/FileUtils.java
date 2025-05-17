package com.snowball.sqlgenesis.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件保存到磁盘
 * @author lihui
 * @date 2025/1/15 17:44
 */
public class FileUtils {
    
    /**
     * 保存内容到文件
     * @param fileName 文件名（不包含扩展名）
     * @param fileType 文件类型（扩展名，如 "java","xml"）
     * @param filePath 保存路径
     * @param content 文件内容
     * @return 保存后的文件完整路径
     * @throws IOException IO异常
     */
    public static String saveFile(String fileName, String fileType, String filePath, String content) throws IOException {
        String extension = fileType.startsWith(".") ? fileType : "." + fileType;

        Path directoryPath = Paths.get(filePath);
        Files.createDirectories(directoryPath);
        

        Path fullPath = directoryPath.resolve(fileName + extension);
        

        Files.writeString(fullPath, content);
        
        return fullPath.toString();
    }
}