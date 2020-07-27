package com.senontech.service;



import com.senontech.entity.ProductType;
import com.senontech.exceptions.ErrorCodeException;

import java.util.List;

/**
 * Created by lmy on 2019/8/15.
 */
public interface IProductTypeService {

    /**
     * 添加设备类型数据
     * @param param
     * @return
     */
    void addProductType(ProductType param) throws ErrorCodeException;

    /**
     * 删除设备类型数据
     * @param equipmentTypeId
     * @return
     */
    void delProductType(Integer equipmentTypeId);

    void delProductTypeList(List<Integer> ids);

    /**
     * 修改设备类型数据
     * @param param
     * @return
     */
    void editProductType(ProductType param) throws ErrorCodeException;



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

    List<ProductType> queryProductTree();



}
