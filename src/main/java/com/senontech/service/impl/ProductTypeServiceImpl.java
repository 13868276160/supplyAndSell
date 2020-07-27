package com.senontech.service.impl;


import com.senontech.dao.IProductTypeDao;
import com.senontech.entity.ProductType;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lmy on 2019/8/15.
 */
@Service
public class ProductTypeServiceImpl implements IProductTypeService {
    @Autowired
    private IProductTypeDao productTypeDao;
    


    /**
     * 添加产线类型数据
     *
     * @param param
     * @return
     */
    @Override
    public void addProductType(ProductType param) throws ErrorCodeException {
        if (this.productTypeDao.checkRepeat(param)) {
            throw new ErrorCodeException(2);
        }
        productTypeDao.addProductType(param);
    }

    /**
     * 删除产线类型数据
     *
     * @param productTypeId
     * @return
     */
    @Override
    public void delProductType(Integer productTypeId) {
        productTypeDao.delProductType(productTypeId);
        List<ProductType> productTypeList = queryProductTypeByParentId(productTypeId);
        if(productTypeList!=null&&productTypeList.size()>0){
            delTree(productTypeList);
        }

    }

    public void delTree(List<ProductType> list){
        for(ProductType e: list){
            productTypeDao.delProductType(e.getProductTypeId());
            List<ProductType> productTypeList = queryProductTypeByParentId(e.getProductTypeId());
            if(productTypeList!=null&&productTypeList.size()>0){
                delTree(productTypeList);
            }
        }
    }


    @Override
    public void delProductTypeList(List<Integer> ids) {
        for(Integer id:ids){
            productTypeDao.delProductType(id);
            List<ProductType> productTypeList = queryProductTypeByParentId(id);
            if(productTypeList!=null&&productTypeList.size()>0){
                delTree(productTypeList);
            }
        }



    }

    /**
     * 修改产线类型数据
     *
     * @param param
     * @return
     */
    @Override
    public void editProductType(ProductType param) throws ErrorCodeException {



        productTypeDao.editProductType(param);
    }

    /**
     * 查询产线类型数据列表
     *
     * @param
     * @return
     */
    @Override
    public List<ProductType> queryProductTypeList() {
        return productTypeDao.queryProductTypeList();
    }

    /**
     * 根据ID查询产线类型信息
     *
     * @param param
     * @return
     */
    @Override
    public ProductType queryProductType(Integer param) {
        return productTypeDao.queryProductType(param);
    }

    @Override
    public List<ProductType> queryProductTypeByParentId(Integer parentId) {
        return productTypeDao.queryProductTypeByParentId(parentId);
    }

    @Override
    public List<ProductType> queryProductTree() {

        //获取顶级节点
        List<ProductType> productTypeList = productTypeDao.queryParentProduct();

        return buildTree(productTypeList);
    }

    /**
     * 递归获取子节点
     * @param list
     * @return
     */
    public List<ProductType> buildTree(List<ProductType> list){
        for(ProductType e: list){
            List<ProductType> productTypeList = queryProductTypeByParentId(e.getProductTypeId());
            if(productTypeList!=null&&productTypeList.size()>0){
                e.setProductTypes(productTypeList);
                buildTree(productTypeList);
            }
        }
        return list;
    }
}
