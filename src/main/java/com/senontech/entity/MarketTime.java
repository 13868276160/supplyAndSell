package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_MARKETTIME", schema = "dbo", catalog = "db_member")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_MARKETTIME SET deFlag = 1 WHERE marketTimeId = ? ")
public class MarketTime {

    private Integer marketTimeId;
    private Integer memberId;
    private Integer productTypeId;
    private String productName;
    private String marketTime;

    @JSONField(serialize = false)
    private Date timestamp;
    @JSONField(serialize = false)
    private Integer deFlag;

    private List<Integer> marketTimeIdList;
    @Transient
    public List<Integer> getMarketTimeIdList() {
        return marketTimeIdList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "MARKETTIMEID", nullable = false)
    public Integer getMarketTimeId() {
        return marketTimeId;
    }

    public void setMarketTimeId(Integer marketTimeId) {
        this.marketTimeId = marketTimeId;
    }

    @Basic
    @Column(name = "MEMBERID", nullable = true)
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "PRODUCTTYPEID", nullable = true)
    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Basic
    @Column(name = "MARKETTIME", nullable = true)
    public String getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(String marketTime) {
        this.marketTime = marketTime;
    }

    @Basic
    @Column(name = "PRODUCTNAME", nullable = true)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "TIMESTAMP", nullable = true)
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "DEFLAG", nullable = true)
    public Integer getDeFlag() {
        return deFlag;
    }

    public void setDeFlag(Integer deFlag) {
        this.deFlag = deFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketTime mt = (MarketTime) o;

        if (marketTimeId != null ? !marketTimeId.equals(mt.marketTimeId) : mt.marketTimeId != null) return false;
        if (memberId != null ? !memberId.equals(mt.memberId) : mt.memberId != null) return false;
        if (productTypeId != null ? !productTypeId.equals(mt.productTypeId) : mt.productTypeId != null) return false;
        if (productName != null ? !productName.equals(mt.productName) : mt.productName != null) return false;
        if (marketTime != null ? !marketTime.equals(mt.marketTime) : mt.marketTime != null) return false;
        if (timestamp != null ? !timestamp.equals(mt.timestamp) : mt.timestamp != null) return false;
        if (deFlag != null ? !deFlag.equals(mt.deFlag) : mt.deFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = marketTimeId != null ? marketTimeId.hashCode() : 0;
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        result = 31 * result + (marketTimeId != null ? marketTimeId.hashCode() : 0);
        result = 31 * result + (productTypeId != null ? productTypeId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (marketTime != null ? marketTime.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (deFlag != null ? deFlag.hashCode() : 0);
        return result;
    }
}
