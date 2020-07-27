package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_TRANSACTION", schema = "dbo", catalog = "db_member")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_TRANSACTION SET deFlag = 1 WHERE supplyId = ? ")
public class Transaction {

    private Integer transactionId;
    private Integer memberId;//卖方Id
    private Integer shopperId;//买方Id
    private Integer supplyId;//供应信息Id
    private Integer demandId;//需求信息Id
    private Double value;//成交量
    private Double transactionPrice;//成交价
    private Double transactionAmount;//成交金额
    private Date transactionTime;//成交时间
    private Date launchTime;//发起时间
    private Integer confirmId;//确认人Id
    private String confirmType;//确认人类型
    private String status;//状态（0取消交易 1交易中 2拒绝 3交易完成 4已支付 5待确认收款）
    @JSONField(serialize = false)
    private Date timestamp;
    @JSONField(serialize = false)
    private Integer deFlag;
    private List<Integer> transactionIdList;

    private String productName;//品名

    private String supplyContacts;//供方联系人
    private String demandContacts;//买方联系人
    private String supplyPhone;//供方联系电话
    private String demandPhone;//买方联系电话
    private String refusalReason;//拒绝理由
    private String province;//省
    private String city;//市
    private String area;//区
    private String township;//乡镇
    private String specificAddress;//具体地址


    @Basic
    @Column(name = "PROVINCE", nullable = true)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Basic
    @Column(name = "CITY", nullable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Basic
    @Column(name = "AREA", nullable = true)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    @Basic
    @Column(name = "TOWNSHIP", nullable = true)
    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }
    @Basic
    @Column(name = "SPECIFICADDRESS", nullable = true)
    public String getSpecificAddress() {
        return specificAddress;
    }

    public void setSpecificAddress(String specificAddress) {
        this.specificAddress = specificAddress;
    }

    @Basic
    @Column(name = "REFUSALREASON", nullable = true)
    public String getRefusalReason() {
        return refusalReason;
    }

    public void setRefusalReason(String refusalReason) {
        this.refusalReason = refusalReason;
    }

    @Transient
    public String getSupplyContacts() {
        return supplyContacts;
    }

    public void setSupplyContacts(String supplyContacts) {
        this.supplyContacts = supplyContacts;
    }
    @Transient
    public String getDemandContacts() {
        return demandContacts;
    }

    public void setDemandContacts(String demandContacts) {
        this.demandContacts = demandContacts;
    }
    @Transient
    public String getSupplyPhone() {
        return supplyPhone;
    }

    public void setSupplyPhone(String supplyPhone) {
        this.supplyPhone = supplyPhone;
    }
    @Transient
    public String getDemandPhone() {
        return demandPhone;
    }

    public void setDemandPhone(String demandPhone) {
        this.demandPhone = demandPhone;
    }

    @Basic
    @Column(name = "CONFIRMTYPE", nullable = true)
    public String getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(String confirmType) {
        this.confirmType = confirmType;
    }



    @Basic
    @Column(name = "PRODUCTNAME", nullable = true)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private Date transactionTimeStart;//开始成交时间
    private Date transactionTimeEnd;//结束成交时间



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "TRANSACTIONID", nullable = false)
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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
    @Column(name = "SHOPPERID", nullable = true)
    public Integer getShopperId() {
        return shopperId;
    }

    public void setShopperId(Integer shopperId) {
        this.shopperId = shopperId;
    }
    @Basic
    @Column(name = "SUPPLYID", nullable = true)
    public Integer getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }
    @Basic
    @Column(name = "DEMANDID", nullable = true)
    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
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
    @Column(name = "TRANSACTIONPRICE", nullable = true)
    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
    @Basic
    @Column(name = "TRANSACTIONAMOUNT", nullable = true)
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    @Basic
    @Column(name = "TRANSACTIONTIME", nullable = true)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
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
    @Column(name = "LAUNCHTIME", nullable = true)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }


    @Basic
    @Column(name = "CONFIRMID", nullable = true)
    public Integer getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(Integer confirmId) {
        this.confirmId = confirmId;
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
    public List<Integer> getTransactionIdList() {
        return transactionIdList;
    }

    public void setTransactionIdList(List<Integer> transactionIdList) {
        this.transactionIdList = transactionIdList;
    }

    @Transient
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getTransactionTimeStart() {
        return transactionTimeStart;
    }

    public void setTransactionTimeStart(Date transactionTimeStart) {
        this.transactionTimeStart = transactionTimeStart;
    }
    @Transient
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getTransactionTimeEnd() {
        return transactionTimeEnd;
    }

    public void setTransactionTimeEnd(Date transactionTimeEnd) {
        this.transactionTimeEnd = transactionTimeEnd;
    }
}
