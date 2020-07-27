package com.senontech.enums;

/**
 * 删除标记枚举
 */
public enum DeFlagEnum {
    /**
     * 已删
     */
    YES((byte) 1),
    /**
     * 未删
     */
    NO((byte) 0);

    private Byte code;

    private DeFlagEnum(Byte code) {
        this.code = code;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }
}
