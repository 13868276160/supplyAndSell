package com.senontech.exceptions;

/**
 * 错误代码异常类
 */
public class ErrorCodeException extends Exception {

    /**
     * 错误代码Int
     */
    private int errorCodeInt;

    public ErrorCodeException(int errorCodeInt) {
        this.errorCodeInt = errorCodeInt;
    }

    public ErrorCodeException(Throwable cause, int errorCodeInt) {
        super(cause);
        this.errorCodeInt = errorCodeInt;
    }

    public int getErrorCodeInt() {
        return errorCodeInt;
    }

    public void setErrorCodeInt(int errorCodeInt) {
        this.errorCodeInt = errorCodeInt;
    }
}
