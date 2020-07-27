package com.senontech.entity;

public class FuzzyQuery {

    private Integer queryType; // 查询类型 0 名称，1 品名，2上市时间
    private String queryFactor; // 查询内容
    private Integer orderType; // 排序方式 0 字母排序，1 区域排序

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public void setQueryFactor(String queryFactor) {
        this.queryFactor = queryFactor;
    }

    public String getQueryFactor() {
        return queryFactor;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
