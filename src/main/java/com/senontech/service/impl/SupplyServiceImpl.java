package com.senontech.service.impl;


import com.senontech.dao.ISupplyDao;
import com.senontech.entity.Supply;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import com.senontech.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class SupplyServiceImpl implements ISupplyService {
    @Autowired
    private ISupplyDao supplyDao;


    @Override
    public void add(Supply param) throws Exception {
        this.supplyDao.add(param);
    }

    @Override
    public void del(Integer demandId) throws Exception {
        this.supplyDao.del(demandId);

    }

    @Override
    public void delList(List<Integer> demandIdList) {
        this.supplyDao.delList(demandIdList);

    }

    @Override
    public void edit(Supply param) throws ErrorCodeException {
        this.supplyDao.edit(param);

    }

    @Override
    public Supply query(Supply param) {
        return supplyDao.query(param);
    }

    @Override
    public List<Supply> queryList() {
        return supplyDao.queryList();
    }

    @Override
    public PageSize queryPageList(PageSize pageSize) throws ErrorCodeException, ParseException {
        return this.supplyDao.queryPageList(pageSize);
    }

    @Override
    public List<Supply> queryListToday() throws ParseException {
        return this.supplyDao.queryListToday();
    }
}
