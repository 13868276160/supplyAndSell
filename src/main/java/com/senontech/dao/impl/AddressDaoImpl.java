package com.senontech.dao.impl;


import com.senontech.dao.IAddressDao;

import com.senontech.entity.Address;
import com.senontech.exceptions.ErrorCodeException;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.*;

@Repository
public class AddressDaoImpl extends BaseDaoImpl implements IAddressDao {


    @Override
    public void add(Address param) throws ErrorCodeException {
        param.setDeFlag(0);
        param.setTimestamp(new Date());
        this.save(param);

    }

    @Override
    public void del(Integer addressId) {

    }

    @Override
    public void delList(List<Integer> addressIdList) {

    }

    @Override
    public void edit(Address param) {

    }

    @Override
    public Address query(Address param) {
        return null;
    }

    @Override
    public List<Address> queryList() {
        return null;
    }
}
