package com.senontech.dao.impl;

import com.alibaba.fastjson.JSONObject;

import com.senontech.dao.*;
import com.senontech.entity.*;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class TransactionDaoImpl extends BaseDaoImpl implements ITransactionDao {

    @Autowired
    private ISupplyDao supplyDao;

    @Autowired
    private IDemandDao demandDao;

    @Autowired
    private IMemberDao memberDao;

    @Autowired
    private IShopperDao shopperDao;


    @Override
    public void add(Transaction param)  {
        param.setStatus("1");
        param.setDeFlag(0);
        param.setLaunchTime(new Date());
        param.setTimestamp(new Date());
        this.save(param);
    }

    @Override
    public void del(Integer transactionId) {
        Transaction transaction = this.getByDeFlag(Transaction.class, transactionId);
        this.delete(transaction);
    }

    @Override
    public void delList(List<Integer> transactionIdList) {
        Query query = this.getSession().createQuery("UPDATE Transaction set deFlag = 1 where transactionId in (:transactionIdList) and deFlag =0");
        query.setParameter("transactionIdList", transactionIdList);
        query.executeUpdate();
    }

    @Override
    public void edit(Transaction param) {
        Transaction transaction = this.getByDeFlag(Transaction.class, param.getTransactionId());
        if (param.getMemberId() != null)
            transaction.setMemberId(param.getMemberId());
        if (param.getRefusalReason() != null)
            transaction.setRefusalReason(param.getRefusalReason());
        if (param.getValue() != null)
            transaction.setValue(param.getValue());
        if (param.getShopperId() != null)
            transaction.setShopperId(param.getShopperId());
        if (param.getDemandId() != null)
            transaction.setDemandId(param.getDemandId());
        if (param.getSupplyId() != null)
            transaction.setSupplyId(param.getSupplyId());
        if (param.getTransactionPrice() != null)
            transaction.setTransactionPrice(param.getTransactionPrice());
        if (param.getTransactionAmount() != null)
            transaction.setTransactionAmount(param.getTransactionAmount());
        if (param.getTransactionTime() != null)
            transaction.setTransactionTime(param.getTransactionTime());
        if (param.getLaunchTime() != null)
            transaction.setLaunchTime(param.getLaunchTime());
        if (param.getUnitType() != null)
            transaction.setUnitType(param.getUnitType());
        if (param.getProvince() != null)
            transaction.setProvince(param.getProvince());
        if (param.getCity()!=null)
            transaction.setCity(param.getCity());
        if (param.getTownship() != null)
            transaction.setTownship(param.getTownship());
        if (param.getSpecificAddress() != null)
            transaction.setSpecificAddress(param.getSpecificAddress());
        if (param.getArea() != null)
            transaction.setArea(param.getArea());
        if (param.getProductName() != null)
            transaction.setProductName(param.getProductName());
        if (param.getStatus() != null) {
            if (param.getStatus().equals("3")) {//交易完成改变信息状态
                transaction.setStatus(param.getStatus());
                if (transaction.getDemandId() != null) {
                    Demand demand = new Demand();
                    demand.setDemandId(transaction.getDemandId());

                    Demand demand1 = demandDao.query(demand);//查询需求信息改变状态
                    demand1.setStatus("1");
                    demandDao.edit(demand1);
                }
                if (transaction.getSupplyId() != null) {


                    Supply supply = new Supply();
                    supply.setSupplyId(transaction.getSupplyId());

                    Supply supply1 = supplyDao.query(supply);//查询供应信息改变状态
                    supply1.setStatus("1");
                    supplyDao.edit(supply1);
                }
                transaction.setTransactionTime(new Date());//更新交易完成时间
            } else {
                transaction.setStatus(param.getStatus());
            }
        }
        this.update(transaction);
    }

    @Override
    public Transaction query(Transaction param) {
        Transaction transaction = this.getByDeFlag(Transaction.class, param.getTransactionId());
        return transaction;
    }

    @Override
    public List<Transaction> queryList() {
        Query query = this.getSession().createQuery("from Transaction where deFlag = 0");
        return query.list();
    }

    @Override
    public PageSize queryPageList(PageSize pageSize)  {

        //如果起始页码或页面大小为空,或者属于异常值,返回所有符合条件数据
        if (pageSize.getPage() == null || pageSize.getPage() <= 0 || pageSize.getSize() == null || pageSize.getSize() <= 0) {
            StringBuilder condition = new StringBuilder("  deFlag =0 ");
            List<Object> list = new ArrayList<Object>();
            //查询条件
            if (pageSize.getCondition() != null) {
                Transaction transaction = JSONObject.parseObject(pageSize.getCondition(), Transaction.class);
                if (transaction.getMemberId() != null) {
                    condition.append(" and memberId =?");
                    list.add(transaction.getMemberId());
                }
                if (transaction.getShopperId() != null) {
                    condition.append(" and shopperId =?");
                    list.add(transaction.getShopperId());
                }
                if (transaction.getSupplyId() != null) {
                    condition.append(" and supplyId =?");
                    list.add(transaction.getSupplyId());
                }
                if (transaction.getDemandId() != null) {
                    condition.append(" and demandId =?");
                    list.add(transaction.getDemandId());
                }
                if (transaction.getStatus() != null) {
                    condition.append(" and status =?");
                    list.add(transaction.getStatus());
                }
                if (transaction.getConfirmId() != null) {
                    condition.append(" and confirmId =?");
                    list.add(transaction.getConfirmId());
                }
                if (transaction.getConfirmType() != null) {
                    condition.append(" and confirmType =?");
                    list.add(transaction.getConfirmType());
                }
                if (transaction.getTransactionTimeStart() != null) {
                    condition.append(" and transactionTime >=?");
                    list.add(transaction.getTransactionTimeStart());
                }
                if (transaction.getTransactionTimeEnd() != null) {
                    condition.append(" and transactionTime <=?");
                    list.add(transaction.getTransactionTimeEnd());
                }
                if (transaction.getProductName() != null) {
                    condition.append(" and productName =?");
                    list.add(transaction.getProductName());
                }
            }

            StringBuilder listhql = new StringBuilder("from Transaction where  " + condition +" order by timestamp desc");


            Query query = this.getSession().createQuery(listhql.toString());
            setQueryParameters(query, list);


            List<Transaction> transactionList = query.list();//获得数据
            for (Transaction transaction1 : transactionList) {//添加买卖双方电话姓名
                if (transaction1.getMemberId() != null) {
                    Member member = new Member();
                    member.setMemberId(transaction1.getMemberId());

                    Member member1 = memberDao.query(member);
                    transaction1.setSupplyContacts(member1.getMemberName());
                    transaction1.setSupplyPhone(member1.getPhone());
                }
                if (transaction1.getShopperId() != null) {
                    Shopper shopper = new Shopper();
                    shopper.setShopperId(transaction1.getShopperId());

                    Shopper shopper1 = shopperDao.query(shopper);
                    transaction1.setDemandContacts(shopper1.getName());
                    transaction1.setDemandPhone(shopper1.getPhone());
                }
            }


            PageSize pageSize1 = new PageSize();
            pageSize1.setList(transactionList);
            return pageSize1;

        } else {
            List<Object> list = new ArrayList<Object>();
            PageSize ps = new PageSize();
            ps.setPage(pageSize.getPage());
            Integer start = (pageSize.getPage() - 1) * pageSize.getSize();

            Transaction transaction = JSONObject.parseObject(pageSize.getCondition(), Transaction.class);

            StringBuilder condition = new StringBuilder("  deFlag =0 ");
            if (transaction.getMemberId() != null) {
                condition.append(" and memberId =?");
                list.add(transaction.getMemberId());
            }
            if (transaction.getShopperId() != null) {
                condition.append(" and shopperId =?");
                list.add(transaction.getShopperId());
            }
            if (transaction.getSupplyId() != null) {
                condition.append(" and supplyId =?");
                list.add(transaction.getSupplyId());
            }
            if (transaction.getDemandId() != null) {
                condition.append(" and demandId =?");
                list.add(transaction.getDemandId());
            }
            if (transaction.getStatus() != null) {
                condition.append(" and status =?");
                list.add(transaction.getStatus());
            }
            if (transaction.getConfirmId() != null) {
                condition.append(" and confirmId =?");
                list.add(transaction.getConfirmId());
            }
            if (transaction.getConfirmType() != null) {
                condition.append(" and confirmType =?");
                list.add(transaction.getConfirmType());
            }
            if (transaction.getTransactionTimeStart() != null) {
                condition.append(" and transactionTime >=?");
                list.add(transaction.getTransactionTimeStart());
            }
            if (transaction.getTransactionTimeEnd() != null) {
                condition.append(" and transactionTime <=?");
                list.add(transaction.getTransactionTimeEnd());
            }
            if (transaction.getProductName() != null) {
                condition.append(" and productName =?");
                list.add(transaction.getProductName());
            }
            if (transaction.getUnitType()!=null){
                condition.append(" and unitType =?");
                list.add(transaction.getUnitType());
            }


            StringBuilder counthql = new StringBuilder("select count(*) from Transaction where  " + condition +" order by timestamp desc");
            StringBuilder listhql = new StringBuilder("from Transaction where  " + condition +" order by timestamp desc");

            Query queryTotal = this.getSession().createQuery(counthql.toString());
            setQueryParameters(queryTotal, list);
            ps.setTotal((Long) queryTotal.uniqueResult());

            Query query = this.getSession().createQuery(listhql.toString());
            setQueryParameters(query, list);
            query.setFirstResult(start);
            query.setMaxResults(pageSize.getSize());

            List<Transaction> transactionList = query.list();//获得数据


            for (Transaction transaction1 : transactionList) {
                if (transaction1.getMemberId() != null) {
                    Member member = new Member();
                    member.setMemberId(transaction1.getMemberId());

                    Member member1 = memberDao.query(member);
                    transaction1.setSupplyContacts(member1.getMemberName());
                    transaction1.setSupplyPhone(member1.getPhone());
                }
                if (transaction1.getShopperId() != null) {
                    Shopper shopper = new Shopper();
                    shopper.setShopperId(transaction1.getShopperId());

                    Shopper shopper1 = shopperDao.query(shopper);
                    transaction1.setDemandContacts(shopper1.getName());
                    transaction1.setDemandPhone(shopper1.getPhone());
                }
            }


            if (transactionList == null || transactionList.size() == 0) {
                ps.setPage(1);
                Query queryFirst = this.getSession().createQuery(listhql.toString());
                setQueryParameters(queryFirst, list);
                queryFirst.setFirstResult(0);
                queryFirst.setMaxResults(pageSize.getSize());
                transactionList = queryFirst.list();

            }
            ps.setList(transactionList);
            return ps;
        }
    }

    @Override
    public List<Transaction> queryListToday() throws ParseException {

        Date startDate1 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate1);
        calendar.add(calendar.MONTH, -1); //把日期往前减一月,整数  往后推,负数往前移动
        Date endDate = calendar.getTime();


        Query query = this.getSession().createQuery("from Transaction where deFlag = 0 and transactionTime >= :endDate and transactionTime <= :nowDate and status in (3,4,5) order by transactionTime desc");
        query.setParameter("endDate", endDate);
        query.setParameter("nowDate", new Date());
        query.setMaxResults(15);
        return query.list();
    }
}
