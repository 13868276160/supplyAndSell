package com.senontech.service.impl;


import com.senontech.dao.IShopperDao;
import com.senontech.entity.Shopper;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import com.senontech.service.IShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopperServiceImpl implements IShopperService {

    @Autowired
    private IShopperDao shopperDao;

    @Override
    public void add(Shopper param) throws ErrorCodeException {
        //检查重复性
        if(this.shopperDao.checkRepeat(param)){
            throw new ErrorCodeException(2);
        }
        this.shopperDao.add(param);
    }

    @Override
    public void del(Integer shopperId) {
        this.shopperDao.del(shopperId);

    }

    @Override
    public void delList(List<Integer> shopperIdList) {
        this.shopperDao.delList(shopperIdList);

    }

    @Override
    public void edit(Shopper param) throws ErrorCodeException {
        this.shopperDao.edit(param);

    }

    @Override
    public Shopper query(Shopper param) {
        return this.shopperDao.query(param);
    }

    @Override
    public List<Shopper> queryList() {
        return this.shopperDao.queryList();
    }

    @Override
    public Boolean checkRepeat(Shopper param) {
        return this.shopperDao.checkRepeat(param);
    }

    @Override
    public Shopper shopperLogin(Shopper param) {
        return this.shopperDao.shopperLogin(param);
    }

    @Override
    public PageSize queryPageList(PageSize pageSize)  {
        return this.shopperDao.queryPageList(pageSize);
    }
}
