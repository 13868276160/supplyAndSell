package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_DEMAND", schema = "dbo", catalog = "db_member")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_DEMAND SET deFlag = 1 WHERE demandId = ? ")
public class Demand {

    private Integer demandId;
    private Integer shopperId;
    private String productName;//品名
    private Double value;//数量（公斤）
    private String contacts;//联系人
    private String phone;//联系电话
    private Date releaseDate;//发布日期
    private Double buyoutPrice;//一口价
    private Double maxPrice;//最高价
    private String status;//状态0未完成 1完成

    private Date endTime;//到期时间
    private Integer day;//时间天数


    @Basic
    @Column(name = "ENDTIME", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "DAY", nullable = true)
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @JSONField(serialize = false)
    private Date timestamp;
    @JSONField(serialize = false)
    private Integer deFlag;
    private List<Integer> demandIdList;

    private Integer priceType;
    @Transient
    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    private Date releaseDateStart;//开始发布日期（用来查询）
    private Date releaseDateEnd;//结束发布日期（用来查询）

    private Double buyoutPriceStart;//起始一口价（用来查询）
    private Double buyoutPriceEnd;//结束一口价（用来查询）

    private Double maxPriceStart;//起始最高价（用来查询）
    private Double maxPriceEnd;//结束最高价（用来查询）

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "DEMANDID", nullable = false)
    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }
    @Basic
    @Column(name = "SHOPPERID", nullable = true)
    public Integer getShopperId() {
        return shopperId;
    }

    public void setShopperId(Integer shopperId) {
        this.shopperId = shopperId;
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
    @Column(name = "VALUE", nullable = true)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    @Basic
    @Column(name = "CONTACTS", nullable = true)
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    @Basic
    @Column(name = "PHONE", nullable = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Basic
    @Column(name = "RELEASEDATE", nullable = true)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    @Basic
    @Column(name = "BUYOUTPRICE", nullable = true)
    public Double getBuyoutPrice() {
        return buyoutPrice;
    }

    public void setBuyoutPrice(Double buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }
    @Basic
    @Column(name = "MAXPRICE", nullable = true)
    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
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

    @Basic
    @Column(name = "STATUS", nullable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeFlag(Integer deFlag) {
        this.deFlag = deFlag;
    }

    @Transient
    public List<Integer> getDemandIdList() {
        return demandIdList;
    }

    public void setDemandIdList(List<Integer> demandIdList) {
        this.demandIdList = demandIdList;
    }

    @Transient
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getReleaseDateStart() {
        return releaseDateStart;
    }

    public void setReleaseDateStart(Date releaseDateStart) {
        this.releaseDateStart = releaseDateStart;
    }
    @Transient
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getReleaseDateEnd() {
        return releaseDateEnd;
    }

    public void setReleaseDateEnd(Date releaseDateEnd) {
        this.releaseDateEnd = releaseDateEnd;
    }
    @Transient
    public Double getBuyoutPriceStart() {
        return buyoutPriceStart;
    }

    public void setBuyoutPriceStart(Double buyoutPriceStart) {
        this.buyoutPriceStart = buyoutPriceStart;
    }
    @Transient
    public Double getBuyoutPriceEnd() {
        return buyoutPriceEnd;
    }

    public void setBuyoutPriceEnd(Double buyoutPriceEnd) {
        this.buyoutPriceEnd = buyoutPriceEnd;
    }
    @Transient
    public Double getMaxPriceStart() {
        return maxPriceStart;
    }

    public void setMaxPriceStart(Double maxPriceStart) {
        this.maxPriceStart = maxPriceStart;
    }
    @Transient
    public Double getMaxPriceEnd() {
        return maxPriceEnd;
    }

    public void setMaxPriceEnd(Double maxPriceEnd) {
        this.maxPriceEnd = maxPriceEnd;
    }
}
