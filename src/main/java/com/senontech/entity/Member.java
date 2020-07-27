package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_MEMBER")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_MEMBER SET deFlag = 1 WHERE memberId = ? ")
public class Member {

    private Integer memberId;
    private Integer areaId; // 所属区域
    private String account;//账号
    private String wechatId;
    private String memberName;// 名称
    @JSONField(serialize = false)
    private String password;//密码
    private Integer isMember;// 是否会员
    private String chargeMan;// 负责人
    private String phone; // 联系号码
    private String address; // 地址
    private Integer scale; // 种植规模
    private Double lng; // 经度
    private Double lat; // 纬度
    private String code;//微信code
    @Transient
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
//    private String feedVendor; // 饲料采购单位

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timestamp;


    private List<Integer> memberIdList;
    @Transient
    public List<Integer> getMemberIdList() {
        return memberIdList;
    }

    public void setMemberIdList(List<Integer> memberIdList) {
        this.memberIdList = memberIdList;
    }


    @JSONField(serialize = false)
    private Integer deFlag;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "MEMBERID", nullable = false)
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "ACCOUNT", nullable = true, length = 255)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "AREAID", nullable = true)
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "MEMBERNAME", nullable = true)
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ISMEMBER", nullable = true)
    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    @Basic
    @Column(name = "CHARGEMAN", nullable = true)
    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
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
    @Column(name = "ADDRESS", nullable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "SCALE", nullable = true)
    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }


    @Basic
    @Column(name = "LNG", nullable = true)
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Basic
    @Column(name = "LAT", nullable = true)
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "WECHATID", nullable = true, length = 255)
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
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

        Member member = (Member) o;

        if (memberId != null ? !member.equals(member.memberId) : member.memberId != null) return false;
        if (areaId != null ? !member.equals(member.areaId) : member.areaId != null) return false;
        if (account != null ? !account.equals(member.account) : member.account != null) return false;
        if (memberName != null ? !member.equals(member.memberName) : member.memberName != null) return false;
        if (password != null ? !password.equals(member.password) : member.password != null) return false;
        if (isMember != null ? !member.equals(member.isMember) : member.isMember != null) return false;
        if (chargeMan != null ? !member.equals(member.chargeMan) : member.chargeMan != null) return false;
        if (phone != null ? !member.equals(member.phone) : member.phone != null) return false;
        if (address != null ? !member.equals(member.address) : member.address != null) return false;
        if (scale != null ? !member.equals(member.scale) : member.scale != null) return false;
        if (lng != null ? !member.equals(member.lng) : member.lng != null) return false;
        if (lat != null ? !member.equals(member.lat) : member.lat != null) return false;
        if (wechatId != null ? !wechatId.equals(member.wechatId) : member.wechatId != null) return false;
        if (timestamp != null ? !timestamp.equals(member.timestamp) : member.timestamp != null) return false;
        if (deFlag != null ? !deFlag.equals(member.deFlag) : member.deFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId != null ? memberId.hashCode() : 0;
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (memberName != null ? memberName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isMember != null ? isMember.hashCode() : 0);
        result = 31 * result + (chargeMan != null ? chargeMan.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (wechatId != null ? wechatId.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (deFlag != null ? deFlag.hashCode() : 0);
        return result;
    }
}
