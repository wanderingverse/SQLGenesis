package com.snowball.sqlgenesis.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author lihui
 * @date 2025/1/14 17:21
 **/
@Getter
public enum StatusCode {

    /**
     * 操作成功
     */
    SUCCESS(HttpStatus.OK.value(), "操作成功"),

    /**
     * 请求参数错误
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "请求参数错误"),

    /**
     * 未授权
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "未授权"),

    /**
     * 禁止访问
     */
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "禁止访问"),

    /**
     * 资源未找到
     */
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "资源未找到"),

    /**
     * 422 Unprocessable Entity - 请求语法正确，但数据无法被服务器处理。
     * <p>该状态码用于表示请求的语法正确，且请求中的数据格式符合要求，但由于数据内容不符合业务规则或其他验证条件，导致服务器无法处理该请求。</p>
     * <ul>
     *     <li><b>字段格式错误：</b>字段的格式虽然符合要求，但值不符合预期。如日期值不符合逻辑或无效性、字符串长度不符合要求。</li>
     *     <li><b>缺少必要字段：</b>请求中缺少业务规则必要的字段</li>
     *     <li><b>数据值不符合业务规则：</b>请求中的数据违反了业务规则。</li>
     *     <li><b>不可接受的操作：</b>请求的数据符合格式要求，但由于某些业务逻辑原因，服务器无法处理。如：重复的用户名，操作已取消的订单。</li>
     *     <li><b>表单验证失败：</b>请求中的数据没有通过验证规则。例如：密码强度不足，邮箱格式不正确。</li>
     *     <li><b>数据冲突：</b>请求提交的数据与现有数据冲突。例如：用户名已存在。</li>
     * </ul>
     */
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY.value(), "无法处理的实体"),

    /**
     * 服务器错误
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误");


    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过状态码获取对应的状态信息
     *
     * @param code 状态码
     * @return message
     */
    public static String getMessageByCode(int code) {
        for (StatusCode status : StatusCode.values()) {
            if (status.getCode() == code) {
                return status.getMessage();
            }
        }
        return "未知的状态码";
    }
}
