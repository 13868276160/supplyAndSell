package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_SUPPLY")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_SUPPLY SET deFlag = 1 WHERE supplyId = ? ")
public class Supply {

    private Integer supplyId;
    private Integer memberId;
    private String productName;//品名
    private Double value;//数量（公斤）
    private String contacts;//联系人
    private String phone;//联系电话
    private Date releaseDate;//发布日期
    private Double price;//价格
    private String priceType;//价格类型
    private String farmName;//农场名
    private String status;//状态
    @JSONField(serialize = false)
    private Date timestamp;
    @JSONField(serialize = false)
    private Integer deFlag;
    private List<Integer> supplyIdList;
    private Date endTime;//到期时间
    private Integer day;//时间天数
    private String unitType;//单位类型（斤 公斤）


    @Basic
    @Column(name = "UNITTYPE", nullable = true)
    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Basic
    @Column(name = "PRICE", nullable = true)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    @Basic
    @Column(name = "PRICETYPE", nullable = true)
    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }


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

    private Date releaseDateStart;//开始发布日期（用来查询）
    private Date releaseDateEnd;//结束发布日期（用来查询）

    private Double priceStart;//起始一口价（用来查询）
    private Double priceEnd;//结束一口价（用来查询）



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "SUPPLYID", nullable = false)
    public Integer getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
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
    @Column(name = "FARMNAME", nullable = true)
    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    @Basic
    @Column(name = "STATUS", nullable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Transient
    public List<Integer> getSupplyIdList() {
        return supplyIdList;
    }

    public void setSupplyIdList(List<Integer> supplyIdList) {
        this.supplyIdList = supplyIdList;
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
    public Double getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Double priceStart) {
        this.priceStart = priceStart;
    }
    @Transient
    public Double getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(Double priceEnd) {
        this.priceEnd = priceEnd;
    }

}
