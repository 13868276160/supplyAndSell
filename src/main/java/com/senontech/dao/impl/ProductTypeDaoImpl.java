package com.senontech.dao.impl;


import com.senontech.dao.IProductTypeDao;
import com.senontech.entity.ProductType;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by lmy on 2019/8/15.
 */
@Repository
public class ProductTypeDaoImpl extends BaseDaoImpl implements IProductTypeDao {


    /**
     * 添加设备类型数据
     *
     * @param param
     * @return
     */
    @Override
    public void addProductType(ProductType param) {
        param.setTimestamp(new Date());
        param.setDeFlag(0);
        this.save(param);
    }

    /**
     * 删除设备类型数据
     *
     * @param productTypeId
     * @return
     */
    @Override
    public void delProductType(Integer productTypeId) {
        ProductType productType = this.getByDeFlag(ProductType.class,productTypeId);
        this.delete(productType);
    }

    /**
     * 修改设备类型数据
     *
     * @param param
     * @return
     */
    @Override
    public void editProductType(ProductType param) {
        ProductType productType = this.getByDeFlag(ProductType.class,param.getProductTypeId());
        if(param.getProductTypeName()!=null) productType.setProductTypeName(param.getProductTypeName());
        if(param.getParentId()!=null) productType.setParentId(param.getParentId());
        this.update(productType);
    }

    /**
     * 查询设备类型数据列表
     *
     * @return
     */
    @Override
    public List<ProductType> queryProductTypeList() {
        String hql = "from ProductType where deFlag = 0";
        Query query = this.getSession().createQuery(hql);
        return query.list();
    }

    /**
     * 根据ID查询设备类型信息
     *
     * @param param
     * @return
     */
    @Override
    public ProductType queryProductType(Integer param) {

        ProductType productType = this.getSession().get(ProductType.class,param);
        if(productType.getDeFlag()==1){
            return  null;
        }

        return productType;
    }

    @Override
    public List<ProductType> queryProductTypeByParentId(Integer parentId) {

        StringBuilder sb = new StringBuilder("from ProductType where deFlag =0 and parentId = ?");
        Query query = this.getSession().createQuery(sb.toString());
        query.setParameter(0,parentId);

        return query.list();
    }

    @Override
    public List<ProductType> queryParentProduct() {

        StringBuilder sb = new StringBuilder("from ProductType where deFlag =0 and parentId = 0 or parentId = null");
        Query query = this.getSession().createQuery(sb.toString());

        return query.list();
    }

    /**
     * 检验是否存在
     *
     * @param param
     */
    @Override
    public Boolean checkRepeat(ProductType param) {
        List<Object> list = new ArrayList<Object>();
        StringBuilder sb = new StringBuilder("select count(*) from ProductType where deFlag =0");
        if(param.getProductTypeName()!=null){
            sb.append(" and productTypeName = ?");
            list.add(param.getProductTypeName());
        }
        if(param.getProductTypeId()!=null){
            sb.append(" and productTypeId != ?");
            list.add(param.getProductTypeId());
        }

        Query query = this.getSession().createQuery(sb.toString());
        setQueryParameters(query,list);
        return (Long)query.uniqueResult()>0;
    }



}
