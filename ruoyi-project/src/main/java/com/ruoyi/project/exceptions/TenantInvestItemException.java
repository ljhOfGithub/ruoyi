package com.ruoyi.project.exceptions;
/**
 * 业务异常
 */
public class TenantInvestItemException extends RuntimeException  {
    private static final long serialVersionUID = -6406707333660833511L;

    private Integer code;
    private String msg;

    public TenantInvestItemException(String errorMessage) {
        super(errorMessage);
        this.code = 404;
        this.msg = errorMessage;
    }
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
