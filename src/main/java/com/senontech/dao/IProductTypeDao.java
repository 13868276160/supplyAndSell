package com.senontech.dao;

import com.senontech.entity.ProductType;

import java.util.List;

/**
 * Created by panjianqiang on 2017/10/17.
 */
public interface IProductTypeDao {
    /**
     * 添加设备类型数据
     * @param param
     * @return
     */
    void addProductType(ProductType param);

    /**
     * 删除设备类型数据
     * @param equipmentTypeId
     * @return
     */
    void delProductType(Integer equipmentTypeId);

    /**
     * 修改设备类型数据
     * @param param
     * @return
     */
    void editProductType(ProductType param);

    /**
     * 查询设备类型数据列表
     * @return
     */
    List<ProductType> queryProductTypeList();

    /**
     * 根据ID查询设备类型信息
     * @return
     */
    ProductType queryProductType(Integer param);

    List<ProductType> queryProductTypeByParentId(Integer parentId);

    List<ProductType> queryParentProduct();

    /**
     *检验是否存在
     */
    Boolean checkRepeat(ProductType param);




}
