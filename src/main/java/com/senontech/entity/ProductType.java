package com.senontech.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by panjianqiang on 2019/1/7.
 * 品种类型
 */
@Entity
@Table(name = "T_PRODUCTTYPE")
@DynamicUpdate
@SQLDelete(sql = "UPDATE T_PRODUCTTYPE SET deFlag = 1 WHERE productTypeId = ? ")
public class ProductType {
    private Integer productTypeId;
    private String productTypeName;//产品类型名称
    @JSONField(serialize = false)
    private Date timestamp;
    @JSONField(serialize = false)
    private Integer deFlag;

    private Integer parentId;//父Id


    private List<ProductType> productTypes;
    private List<Integer> productTypeIds;


    @Transient
    public List<Integer> getProductTypeIds() {
        return productTypeIds;
    }

    public void setProductTypeIds(List<Integer> productTypeIds) {
        this.productTypeIds = productTypeIds;
    }

    @Transient
    public List<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    @Basic
    @Column(name = "PARENTID", nullable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "EQUIPMENTTYPEID", nullable = false)
    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Basic
    @Column(name = "EQUIPMENTTYPENAME", nullable = true, length = 255)
    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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

        ProductType that = (ProductType) o;

        if (productTypeId != null ? !productTypeId.equals(that.productTypeId) : that.productTypeId != null)
            return false;
        if (productTypeName != null ? !productTypeName.equals(that.productTypeName) : that.productTypeName != null)
            return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (deFlag != null ? !deFlag.equals(that.deFlag) : that.deFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productTypeId != null ? productTypeId.hashCode() : 0;
        result = 31 * result + (productTypeName != null ? productTypeName.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (deFlag != null ? deFlag.hashCode() : 0);
        return result;
    }
}
