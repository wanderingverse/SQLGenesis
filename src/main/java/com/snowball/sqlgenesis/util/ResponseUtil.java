package com.snowball.sqlgenesis.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.snowball.sqlgenesis.common.StatusCode;
import lombok.*;

import java.util.Optional;

/**
 * <p>统一响应工具类</p>
 * <p>用于标准化返回给前端的响应格式</p>
 * <p>通过不同的构造方法返回成功或错误的响应信息</p>
 * @author lihui
 * @param <T> 响应的数据类型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUtil<T> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间戳
     */
    private long timestamp;

    /**
     * <p>成功响应，无返回数据</p>
     *
     * @param <T> 返回的数据类型
     * @return 标准化的成功响应（不带数据）
     */
    public static <T> ResponseUtil<T> success() {
        return success(null);
    }

    /**
     * <p>成功响应，带返回数据</p>
     *
     * @param <T>  返回的数据类型
     * @param data 响应数据
     * @return 标准化的成功响应（带数据）
     */
    public static <T> ResponseUtil<T> success(T data) {
        return ResponseUtil.<T>builder()
                           .code(StatusCode.SUCCESS.getCode())
                           .message(StatusCode.SUCCESS.getMessage())
                           .data(data)
                           .timestamp(System.currentTimeMillis())
                           .build();
    }

    /**
     * <p>错误响应，无返回数据</p>
     *
     * @param <T>        返回的数据类型
     * @param statusCode 错误的状态码
     * @return 标准化的失败响应（不带数据）
     */
    public static <T> ResponseUtil<T> error(StatusCode statusCode) {
        return error(statusCode, null);
    }

    /**
     * <p>错误响应，带返回数据</p>
     *
     * @param <T>        返回的数据类型
     * @param statusCode 错误的状态码
     * @param data       错误的响应数据
     * @return 标准化的失败响应（带数据）
     */
    public static <T> ResponseUtil<T> error(StatusCode statusCode, T data) {
        return ResponseUtil.<T>builder()
                           .code(statusCode.getCode())
                           .message(statusCode.getMessage())
                           .data(data)
                           .timestamp(System.currentTimeMillis())
                           .build();
    }

    /**
     * <p>错误响应，自定义响应码和信息</p>
     *
     * @param <T>     返回的数据类型
     * @param code    自定义响应码
     * @param message 自定义响应消息
     * @return 标准化的失败响应（不带数据）
     */
    public static <T> ResponseUtil<T> error(int code, String message) {
        return ResponseUtil.<T>builder()
                           .code(code)
                           .message(message)
                           .data(null)
                           .timestamp(System.currentTimeMillis())
                           .build();
    }
}
