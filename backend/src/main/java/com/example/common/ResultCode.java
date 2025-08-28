package com.example.common;

import lombok.Getter;

/**
 * 统一结果码枚举
 * 定义系统中所有可能的响应状态码
 * 
 * @author xuzili
 */
@Getter
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(200, "操作成功"),

    /* 客户端错误：4xx */
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    VALIDATE_FAILED(422, "参数校验失败"),

    /* 服务端错误：5xx */
    INTERNAL_SERVER_ERROR(500, "系统内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    /* 业务错误码：1xxx */
    USER_NOT_FOUND(1001, "用户不存在"),
    USER_ALREADY_EXISTS(1002, "用户已存在"),
    USERNAME_OR_PASSWORD_ERROR(1003, "用户名或密码错误"),
    USER_ACCOUNT_DISABLED(1004, "用户账户已禁用"),
    USER_ACCOUNT_LOCKED(1005, "用户账户已锁定"),

    /* 数据库相关错误：2xxx */
    DATABASE_ERROR(2001, "数据库操作失败"),
    DATA_INTEGRITY_VIOLATION(2002, "数据完整性违反"),
    DUPLICATE_KEY_ERROR(2003, "数据重复"),

    /* 第三方服务错误：3xxx */
    EXTERNAL_SERVICE_ERROR(3001, "外部服务调用失败"),
    NETWORK_TIMEOUT(3002, "网络超时"),

    /* 文件操作错误：4xxx */
    FILE_UPLOAD_ERROR(4001, "文件上传失败"),
    FILE_NOT_FOUND(4002, "文件不存在"),
    FILE_SIZE_EXCEEDED(4003, "文件大小超出限制");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据状态码获取枚举
     */
    public static ResultCode getByCode(Integer code) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }
}
