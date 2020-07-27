package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_ADDRESS", schema = "dbo", catalog = "db_member")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_ADDRESS SET deFlag = 1 WHERE addressId = ? ")
public class Address {

    private Integer addressId;
    private Integer Id;//id
    private String roleType;//角色类型（买方，买方）
    private String province;//省
    private String city;//市
    private String area;//区
    private String township;//乡镇
    private String specificAddress;//具体地址
    @JSONField(serialize = false)
    private Date timestamp;
    @JSONField(serialize = false)
    private Integer deFlag;
    private List<Integer> addressIdList;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "ADDRESSID", nullable = false)
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    @Basic
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
    @Basic
    @Column(name = "ROLETYPE", nullable = false)
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
    @Basic
    @Column(name = "PROVINCE", nullable = false)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Basic
    @Column(name = "CITY", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Basic
    @Column(name = "AREA", nullable = false)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    @Basic
    @Column(name = "TOWNSHIP", nullable = false)
    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }
    @Basic
    @Column(name = "SPECIFICADDRESS", nullable = false)
    public String getSpecificAddress() {
        return specificAddress;
    }

    public void setSpecificAddress(String specificAddress) {
        this.specificAddress = specificAddress;
    }
    @Basic
    @Column(name = "TIMESTAMP", nullable = false)
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    @Basic
    @Column(name = "DEFLAG", nullable = false)
    public Integer getDeFlag() {
        return deFlag;
    }

    public void setDeFlag(Integer deFlag) {
        this.deFlag = deFlag;
    }
    @Transient
    public List<Integer> getAddressIdList() {
        return addressIdList;
    }

    public void setAddressIdList(List<Integer> addressIdList) {
        this.addressIdList = addressIdList;
    }
}
