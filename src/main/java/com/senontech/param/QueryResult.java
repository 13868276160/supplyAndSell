package com.senontech.param;

import java.util.List;

public class QueryResult {
    private List<?> rows;
    private Float maxValue;
    private Float minValue;

    public Float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Float maxValue) {

        this.maxValue =  Float.parseFloat(String.format("%.4f", Float.parseFloat(String.valueOf(maxValue))));
    }

    public Float getMinValue() {
        return minValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = Float.parseFloat(String.format("%.4f", Float.parseFloat(String.valueOf(minValue))));;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }


}
