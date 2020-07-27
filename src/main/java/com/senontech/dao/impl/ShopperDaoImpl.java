package com.senontech.dao.impl;

import com.alibaba.fastjson.JSONObject;

import com.senontech.dao.IShopperDao;
import com.senontech.entity.Shopper;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ShopperDaoImpl extends BaseDaoImpl implements IShopperDao {
    @Override
    public void add(Shopper param) {
        param.setDeFlag(0);
        param.setTimestamp(new Date());
        this.save(param);
    }

    @Override
    public void del(Integer shopperId) {
        Shopper shopper = this.getByDeFlag(Shopper.class, shopperId);
        this.delete(shopper);
    }

    @Override
    public void delList(List<Integer> shopperIdList) {
        Query query = this.getSession().createQuery("UPDATE Shopper set deFlag = 1 where shopperId in (:shopperIdList) and deFlag =0");
        query.setParameter("shopperIdList", shopperIdList);
        query.executeUpdate();
    }

    @Override
    public void edit(Shopper param) throws ErrorCodeException {
        Shopper shopper = this.getByDeFlag(Shopper.class, param.getShopperId());
        if (param.getAccount() != null)
            shopper.setAccount(param.getAccount());
        if (param.getPassword() != null && param.getNewPassword() != null) {

            if (shopper.getAccount().equals(param.getAccount()) && shopper.getPassword().equals(param.getPassword())) {
                shopper.setPassword(param.getNewPassword());
            } else {
                throw new ErrorCodeException(6);
            }
        }
        if (param.getName() != null)
            shopper.setName(param.getName());
        if (param.getEmail() != null)
            shopper.setEmail(param.getEmail());
        if (param.getPhone() != null)
            shopper.setPhone(param.getPhone());
        if (param.getAddress() != null)
            shopper.setAddress(param.getAddress());
        this.update(shopper);
    }

    @Override
    public Shopper query(Shopper param) {
        Shopper shopper = this.getByDeFlag(Shopper.class, param.getShopperId());
        return shopper;
    }

    @Override
    public List<Shopper> queryList() {
        Query query = this.getSession().createQuery("from Shopper where deFlag = 0 order by shopperId desc");
        return query.list();
    }

    @Override
    public Boolean checkRepeat(Shopper param) {
        Query query = this.getSession().createQuery("select count(*) from Shopper where account = :account and deFlag =0");
        query.setParameter("account", param.getAccount());
        return (Long) query.uniqueResult() > 0;
    }

    @Override
    public Shopper shopperLogin(Shopper param) {
        Query query = this.getSession().createQuery(" from Shopper where account = :account and password = :password and deFlag =0");
        query.setParameter("account", param.getAccount()).setParameter("password", param.getPassword());
        query.setMaxResults(1);
        Shopper shopper = (Shopper) query.uniqueResult();
        return shopper;
    }

    @Override
    public PageSize queryPageList(PageSize pageSize)  {
        List<Object> list = new ArrayList<Object>();
        PageSize ps = new PageSize();
        ps.setPage(pageSize.getPage());
        Integer start = (pageSize.getPage() - 1) * pageSize.getSize();

        Shopper shopper = JSONObject.parseObject(pageSize.getCondition(), Shopper.class);

        StringBuilder condition = new StringBuilder("  deFlag =0 ");
        if (shopper.getShopperId() != null) {
            condition.append(" and shopperId =?");
            list.add(shopper.getShopperId());
        }
        if (shopper.getAccount() != null) {
            condition.append(" and account =?");
            list.add(shopper.getAccount());
        }
        if (shopper.getName() != null) {
            condition.append(" and name =?");
            list.add(shopper.getName());
        }
        if (shopper.getPhone() != null) {
            condition.append(" and phone =?");
            list.add(shopper.getPhone());
        }
        if (shopper.getEmail() != null) {
            condition.append(" and email =?");
            list.add(shopper.getEmail());
        }

        StringBuilder counthql = new StringBuilder("select count(*) from Shopper where  " + condition);
        StringBuilder listhql = new StringBuilder("from Shopper where  " + condition);

        Query queryTotal = this.getSession().createQuery(counthql.toString());
        setQueryParameters(queryTotal, list);
        ps.setTotal((Long) queryTotal.uniqueResult());

        Query query = this.getSession().createQuery(listhql.toString());
        setQueryParameters(query, list);
        query.setFirstResult(start);
        query.setMaxResults(pageSize.getSize());

        List<Shopper> shopperList = query.list();
        if (shopperList == null || shopperList.size() == 0) {
            ps.setPage(1);
            Query queryFirst = this.getSession().createQuery(listhql.toString());
            setQueryParameters(queryFirst, list);
            queryFirst.setFirstResult(0);
            queryFirst.setMaxResults(pageSize.getSize());
            shopperList = queryFirst.list();
        }
        ps.setList(shopperList);
        return ps;
    }
}
