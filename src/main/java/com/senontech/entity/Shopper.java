package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_SHOPPER", schema = "dbo", catalog = "db_member")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_SHOPPER SET deFlag = 1 WHERE shopperId = ? ")
public class Shopper {
    private Integer shopperId;
    private String account;//账号
    @JSONField(serialize = false)
    private String password;//密码
    private String name;//姓名
    private String phone;//电话
    private String email;//邮箱
    private String address;// 地址
    @JSONField(serialize = false)
    private Date timestamp;
    @JSONField(serialize = false)
    private Integer deFlag;
    private List<Integer> shopperIdList;


    private String newPassword;//新密码
    @Transient
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "SHOPPERID", nullable = false)
    public Integer getShopperId() {
        return shopperId;
    }

    public void setShopperId(Integer shopperId) {
        this.shopperId = shopperId;
    }
    @Basic
    @Column(name = "ACCOUNT", nullable = true)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    @Basic
    @Column(name = "PASSWORD", nullable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Basic
    @Column(name = "NAME", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "EMAIL", nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public List<Integer> getShopperIdList() {
        return shopperIdList;
    }

    public void setShopperIdList(List<Integer> shopperIdList) {
        this.shopperIdList = shopperIdList;
    }
}
