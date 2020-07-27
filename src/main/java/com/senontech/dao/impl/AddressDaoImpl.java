package com.senontech.dao.impl;


import com.senontech.dao.IAddressDao;

import com.senontech.entity.Address;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AddressDaoImpl extends BaseDaoImpl implements IAddressDao {


    @Override
    public void add(Address param)  {
        param.setDeFlag(0);
        param.setTimestamp(new Date());
        this.save(param);

    }

    @Override
    public void del(Integer addressId) {
        Address address = this.getByDeFlag(Address.class, addressId);
        this.delete(address);

    }

    @Override
    public void delList(List<Integer> addressIdList) {
        Query query = this.getSession().createQuery("UPDATE Address set deFlag = 1 where addressId in (:addressIdList) and deFlag =0");
        query.setParameter("addressIdList", addressIdList);
        query.executeUpdate();

    }

    @Override
    public void edit(Address param) {
        Address address = this.getByDeFlag(Address.class, param.getAddressId());
        if (param.getArea() != null)
            address.setArea(param.getArea());
        if (param.getCity() != null)
            address.setCity(param.getCity());
        if (param.getProvince() != null)
            address.setProvince(param.getProvince());
        if (param.getTownship() != null)
            address.setTownship(param.getTownship());
        if (param.getSpecificAddress() != null)
            address.setSpecificAddress(param.getSpecificAddress());
        this.update(address);

    }

    @Override
    public Address query(Address param) {
        Address address = this.getByDeFlag(Address.class, param.getAddressId());
        return address;
    }

    @Override
    public List<Address> queryList() {
        Query query = this.getSession().createQuery("from Address where deFlag = 0");
        return query.list();
    }

    @Override
    public List<Address> queryListByCondition(Address param) {
        StringBuilder condition = new StringBuilder("  deFlag =0 ");
        List<Object> list = new ArrayList<Object>();

        if (param.getId() != null) {
            condition.append(" and id =?");
            list.add(param.getId());
        }
        if (param.getRoleType() != null) {
            condition.append(" and roleType =?");
            list.add(param.getRoleType());
        }
        if (param.getProvince() != null) {
            condition.append(" and province =?");
            list.add(param.getProvince());
        }
        if (param.getCity() != null) {
            condition.append(" and city =?");
            list.add(param.getCity());
        }
        if (param.getTownship() != null) {
            condition.append(" and township =?");
            list.add(param.getTownship());
        }
        if (param.getArea() != null) {
            condition.append(" and area =?");
            list.add(param.getArea());
        }

        StringBuilder listhql = new StringBuilder("from Address where  " + condition);

        Query query = this.getSession().createQuery(listhql.toString());
        setQueryParameters(query, list);
        return query.list();
    }
}
