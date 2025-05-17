package com.snowball.sqlgenesis.common.ex;


import com.snowball.sqlgenesis.common.StatusCode;
import com.snowball.sqlgenesis.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

/**
 * 全局异常处理器类
 * @author lihui
 * @date 2025/1/16 17:19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
}
