package com.senontech.service.impl;

import com.senontech.dao.IAddressDao;
import com.senontech.entity.Address;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private IAddressDao addressDao;


    @Override
    public void add(Address param)  {
        this.addressDao.add(param);
    }

    @Override
    public void del(Integer addressId) {
        this.addressDao.del(addressId);
    }

    @Override
    public void delList(List<Integer> addressIdList) {
        this.addressDao.delList(addressIdList);
    }

    @Override
    public void edit(Address param) {
        this.addressDao.edit(param);
    }

    @Override
    public Address query(Address param) {
        return this.addressDao.query(param);
    }

    @Override
    public List<Address> queryList() {
        return this.addressDao.queryList();
    }

    @Override
    public List<Address> queryListByCondition(Address param) {
        return this.addressDao.queryListByCondition(param);
    }
}
