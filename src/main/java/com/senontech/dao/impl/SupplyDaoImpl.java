package com.senontech.dao.impl;

import com.alibaba.fastjson.JSONObject;

import com.senontech.dao.ISupplyDao;
import com.senontech.dao.ITransactionDao;
import com.senontech.entity.Supply;
import com.senontech.entity.Transaction;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class SupplyDaoImpl extends BaseDaoImpl implements ISupplyDao {

    @Autowired
    private ITransactionDao transactionDao;


    @Override
    public void add(Supply param) throws ErrorCodeException {
        param.setStatus("0");
        Date date = new Date();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, param.getDay()); //把日期往后增加一天,整数  往后推,负数往前移动
        Date endDate = calendar.getTime();

        param.setReleaseDate(date);

        param.setEndTime(endDate);


        param.setDeFlag(0);
        param.setTimestamp(date);
        this.save(param);
    }

    @Override
    public void del(Integer supplyId) {
        Supply supply = this.getByDeFlag(Supply.class, supplyId);
        this.delete(supply);
    }

    @Override
    public void delList(List<Integer> supplyIdList) {
        Query query = this.getSession().createQuery("UPDATE Supply set deFlag = 1 where supplyId in (:supplyIdList) and deFlag =0");
        query.setParameter("supplyIdList", supplyIdList);
        query.executeUpdate();
    }

    @Override
    public void edit(Supply param) {
        Supply supply = this.getByDeFlag(Supply.class, param.getSupplyId());
        if (param.getMemberId() != null)
            supply.setMemberId(param.getMemberId());
        if (param.getValue() != null)
            supply.setValue(param.getValue());
        if (param.getProductName() != null)
            supply.setProductName(param.getProductName());
        if (param.getContacts() != null)
            supply.setContacts(param.getContacts());
        if (param.getPhone() != null)
            supply.setPhone(param.getPhone());
        if (param.getReleaseDate() != null)
            supply.setReleaseDate(param.getReleaseDate());
        if (param.getBuyoutPrice() != null) {
            supply.setBuyoutPrice(null);//先删后改
            supply.setStartingPrice(null);
            supply.setBuyoutPrice(param.getBuyoutPrice());
        }
        if (param.getStartingPrice() != null) {
            supply.setBuyoutPrice(null);//先删后改
            supply.setStartingPrice(null);
            supply.setStartingPrice(param.getStartingPrice());
        }
        if (param.getFarmName() != null)
            supply.setFarmName(param.getFarmName());
        if (param.getStatus() != null)
            supply.setStatus(param.getStatus());
        if (param.getDay()!=null){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(supply.getReleaseDate());
            calendar.add(calendar.DATE, param.getDay()); //把日期往后增加一天,整数  往后推,负数往前移动
            Date endDate = calendar.getTime();
            supply.setEndTime(endDate);
        }
        this.update(supply);
    }

    @Override
    public Supply query(Supply param) {
        Supply supply = this.getByDeFlag(Supply.class, param.getSupplyId());
        return supply;
    }

    @Override
    public List<Supply> queryList() {
        Query query = this.getSession().createQuery("from Supply where deFlag = 0");
        return query.list();
    }

    @Override
    public PageSize queryPageList(PageSize pageSize) throws ErrorCodeException, ParseException {

        //如果起始页码或页面大小为空,或者属于异常值,直接条件查询所有数据
        if (pageSize.getPage() == null || pageSize.getPage() <= 0 || pageSize.getSize() == null || pageSize.getSize() <= 0) {
            StringBuilder condition = new StringBuilder("  deFlag =0 ");
            List<Object> list = new ArrayList<Object>();
            List<Integer> supplyIdList = new ArrayList<>();
            supplyIdList.add(0);


//            if (pageSize.getShopperId()!=null){
//                Query query = this.getSession().createQuery("from Transaction where deFlag = 0 and shopperId = :shopperId");
//                query.setParameter("shopperId",pageSize.getShopperId());
//                List<Transaction> transactionList=query.list();
//
//                if (transactionList!=null) {
//                    for (Transaction transaction : transactionList) {
//                        condition.append(" and supplyId <> ?");
//                        list.add(transaction.getSupplyId());
//                    }
//                }
//            }


            if (pageSize.getCondition() != null) {
                Supply supply = JSONObject.parseObject(pageSize.getCondition(), Supply.class);
                if (supply.getMemberId() != null) {
                    condition.append(" and memberId =?");
                    list.add(supply.getMemberId());
                }
                if (supply.getReleaseDateStart() != null) {
                    condition.append(" and releaseDate >=?");
                    list.add(supply.getReleaseDateStart());
                }
                if (supply.getReleaseDateEnd() != null) {
                    condition.append(" and releaseDate <=?");
                    list.add(supply.getReleaseDateEnd());
                }
                if (supply.getProductName() != null) {
                    condition.append(" and productName =?");
                    list.add(supply.getProductName());
                }
                if (supply.getContacts() != null) {
                    condition.append(" and contacts =?");
                    list.add(supply.getContacts());
                }
                if (supply.getBuyoutPriceStart() != null) {
                    condition.append(" and buyoutPrice >=?");
                    list.add(supply.getBuyoutPriceStart());
                }
                if (supply.getBuyoutPriceEnd() != null) {
                    condition.append(" and buyoutPrice <=?");
                    list.add(supply.getBuyoutPriceEnd());
                }
                if (supply.getStartingPriceStart() != null) {
                    condition.append(" and startingPrice >=?");
                    list.add(supply.getStartingPriceStart());
                }
                if (supply.getStartingPriceEnd() != null) {
                    condition.append(" and startingPrice =?");
                    list.add(supply.getStartingPriceEnd());
                }
                if (supply.getStatus() != null) {
                    condition.append(" and status =?");
                    list.add(supply.getStatus());
                }
                if (supply.getFarmName() != null) {
                    condition.append(" and farmName =?");
                    list.add(supply.getFarmName());
                }
                if (supply.getPriceType() != null) {
                    if (supply.getPriceType() == 0) {
                        condition.append(" and buyoutPrice is not null");
                    } else {
                        condition.append(" and maxPrice is not null");
                    }
                }
            }
            condition.append(" and supplyId not in (:supplyIdList)");
            if (pageSize.getShopperId() != null) {//查询交易中的订单 排除买家交易中供应信息
                Query query = this.getSession().createQuery("from Transaction where deFlag = 0 and shopperId = :shopperId and status = 1");
                query.setParameter("shopperId", pageSize.getShopperId());
                List<Transaction> transactionList = query.list();


                if (transactionList.size() > 0) {

                    for (Transaction transaction : transactionList) {
                        if (transaction.getSupplyId() != null) {
                            supplyIdList.add(transaction.getSupplyId());
                        }
                    }
                }
            }


            condition.append(" and endTime >= :nowDate");
            StringBuilder listhql = new StringBuilder("from Supply where  " + condition +" order by timestamp desc");

            Query query = this.getSession().createQuery(listhql.toString());
            setQueryParameters(query, list);
            query.setParameter("nowDate", new Date());
            query.setParameter("supplyIdList", supplyIdList);

            List<Supply> transactionList = query.list();
            PageSize pageSize1 = new PageSize();
            pageSize1.setList(transactionList);
            return pageSize1;
        } else {
            //传入Page Size
            List<Integer> supplyIdList = new ArrayList<>();
            supplyIdList.add(0);
            List<Object> list = new ArrayList<Object>();
            PageSize ps = new PageSize();
            ps.setPage(pageSize.getPage());
            Integer start = (pageSize.getPage() - 1) * pageSize.getSize();
            StringBuilder condition = new StringBuilder("  deFlag =0 ");

            if (pageSize.getCondition() != null) {
                Supply supply = JSONObject.parseObject(pageSize.getCondition(), Supply.class);
                if (supply.getMemberId() != null) {
                    condition.append(" and memberId =?");
                    list.add(supply.getMemberId());
                }
                if (supply.getReleaseDateStart() != null) {
                    condition.append(" and releaseDate >=?");
                    list.add(supply.getReleaseDateStart());
                }
                if (supply.getReleaseDateEnd() != null) {
                    condition.append(" and releaseDate <=?");
                    list.add(supply.getReleaseDateEnd());
                }
                if (supply.getProductName() != null) {
                    condition.append(" and productName =?");
                    list.add(supply.getProductName());
                }
                if (supply.getContacts() != null) {
                    condition.append(" and contacts =?");
                    list.add(supply.getContacts());
                }
                if (supply.getBuyoutPriceStart() != null) {
                    condition.append(" and buyoutPrice >=?");
                    list.add(supply.getBuyoutPriceStart());
                }
                if (supply.getBuyoutPriceEnd() != null) {
                    condition.append(" and buyoutPrice <=?");
                    list.add(supply.getBuyoutPriceEnd());
                }
                if (supply.getStartingPriceStart() != null) {
                    condition.append(" and startingPrice >=?");
                    list.add(supply.getStartingPriceStart());
                }
                if (supply.getStartingPriceEnd() != null) {
                    condition.append(" and startingPrice =?");
                    list.add(supply.getStartingPriceEnd());
                }
                if (supply.getStatus() != null) {
                    condition.append(" and status =?");
                    list.add(supply.getStatus());
                }
                if (supply.getFarmName() != null) {
                    condition.append(" and farmName =?");
                    list.add(supply.getFarmName());
                }
                if (supply.getPriceType() != null) {
                    if (supply.getPriceType() == 0) {
                        condition.append(" and buyoutPrice is not null");
                    } else {
                        condition.append(" and maxPrice is not null");
                    }
                }
            }
            condition.append(" and supplyId not in (:supplyIdList)");
            if (pageSize.getShopperId() != null) {
                Query query = this.getSession().createQuery("from Transaction where deFlag = 0 and shopperId = :shopperId and status = 1");
                query.setParameter("shopperId", pageSize.getShopperId());
                List<Transaction> transactionList = query.list();

                if (transactionList.size() > 0) {

                    for (Transaction transaction : transactionList) {
                        if (transaction.getSupplyId() != null) {
                            supplyIdList.add(transaction.getSupplyId());
                        }
                    }
                }
            }
//            if (pageSize.getShopperId()!=null){
//                Query query = this.getSession().createQuery("from Transaction where deFlag = 0 and shopperId = :shopperId");
//                query.setParameter("shopperId",pageSize.getShopperId());
//                List<Transaction> transactionList=query.list();
//
//                if (transactionList!=null) {
//                    for (Transaction transaction : transactionList) {
//                        condition.append(" and supplyId <> ?");
//                        list.add(transaction.getSupplyId());
//                    }
//                }
//            }
            condition.append(" and endTime >= :nowDate");
            StringBuilder counthql = new StringBuilder("select count(*) from Supply where  " + condition +" order by timestamp desc");
            StringBuilder listhql = new StringBuilder("from Supply where  " + condition +" order by timestamp desc");

            Query queryTotal = this.getSession().createQuery(counthql.toString());
            setQueryParameters(queryTotal, list);
            queryTotal.setParameter("nowDate", new Date());


            queryTotal.setParameter("supplyIdList", supplyIdList);

            ps.setTotal((Long) queryTotal.uniqueResult());

            Query query = this.getSession().createQuery(listhql.toString());
            setQueryParameters(query, list);
            query.setParameter("nowDate", new Date());


            query.setParameter("supplyIdList", supplyIdList);

            query.setFirstResult(start);
            query.setMaxResults(pageSize.getSize());

            List<Supply> supplyList = query.list();
            if (supplyList == null || supplyList.size() == 0) {
                ps.setPage(1);
                Query queryFirst = this.getSession().createQuery(listhql.toString());
                setQueryParameters(queryFirst, list);
                queryFirst.setParameter("nowDate", new Date());


                queryFirst.setParameter("supplyIdList", supplyIdList);

                queryFirst.setFirstResult(0);
                queryFirst.setMaxResults(pageSize.getSize());
                supplyList = queryFirst.list();
            }
            ps.setList(supplyList);
            return ps;
        }
    }

    @Override
    public List<Supply> queryListToday() throws ParseException {

        Query query = this.getSession().createQuery("from Supply where deFlag = 0 and endTime >= :nowDate and status = 0");
        query.setParameter("nowDate", new Date());

        return query.list();
    }
}
