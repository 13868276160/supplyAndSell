package com.senontech.dao.impl;

import com.alibaba.fastjson.JSONObject;

import com.senontech.dao.IDemandDao;
import com.senontech.entity.Demand;
import com.senontech.entity.Transaction;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class DemandDaoImpl extends BaseDaoImpl implements IDemandDao {
    @Override
    public void add(Demand param) {
        param.setStatus("0");
        param.setDeFlag(0);
        Date date = new Date();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, param.getDay()); //把日期往后增加一天,整数  往后推,负数往前移动
        Date endDate = calendar.getTime();


        param.setReleaseDate(date);

        param.setEndTime(endDate);
        param.setTimestamp(date);
        this.save(param);
    }

    @Override
    public void del(Integer demandId) {
        Demand demand = this.getByDeFlag(Demand.class, demandId);
        this.delete(demand);
    }

    @Override
    public void delList(List<Integer> demandIdList) {
        Query query = this.getSession().createQuery("UPDATE Demand set deFlag = 1 where demandId in (:demandIdList) and deFlag =0");
        query.setParameter("demandIdList", demandIdList);
        query.executeUpdate();
    }

    @Override
    public void edit(Demand param) {
        Demand demand = this.getByDeFlag(Demand.class, param.getDemandId());
        if (param.getShopperId() != null)
            demand.setShopperId(param.getShopperId());
        if (param.getValue() != null)
            demand.setValue(param.getValue());
        if (param.getContacts() != null)
            demand.setContacts(param.getContacts());
        if (param.getPhone() != null)
            demand.setPhone(param.getPhone());
        if (param.getReleaseDate() != null)
            demand.setReleaseDate(param.getReleaseDate());
        if (param.getBuyoutPrice() != null) {//先删后改
            demand.setMaxPrice(null);
            demand.setBuyoutPrice(null);
            demand.setBuyoutPrice(param.getBuyoutPrice());
        }
        if (param.getMaxPrice() != null) {//先删后改
            demand.setMaxPrice(null);
            demand.setBuyoutPrice(null);
            demand.setMaxPrice(param.getMaxPrice());
        }
        if (param.getStatus() != null)
            demand.setStatus(param.getStatus());
        if (param.getDay()!=null){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(demand.getReleaseDate());
            calendar.add(calendar.DATE, param.getDay()); //把日期往后增加一天,整数  往后推,负数往前移动
            Date endDate = calendar.getTime();
            demand.setEndTime(endDate);
        }
        this.update(demand);

    }

    @Override
    public Demand query(Demand param) {
        Demand demand = this.getByDeFlag(Demand.class, param.getDemandId());
        return demand;
    }

    @Override
    public List<Demand> queryList() {
        Query query = this.getSession().createQuery("from Demand where deFlag = 0");
        return query.list();
    }

    @Override
    public PageSize queryPageList(PageSize pageSize)  {




        //如果起始页码或页面大小为空,或者属于异常值,返回所有符合条件数据
        if (pageSize.getPage() == null || pageSize.getPage() <= 0 || pageSize.getSize() == null || pageSize.getSize() <= 0) {
            StringBuilder condition = new StringBuilder("  deFlag =0 ");
            List<Integer> demandIdList = new ArrayList<>();
            demandIdList.add(0);
            List<Object> list = new ArrayList<Object>();
            if (pageSize.getCondition() != null) {
                Demand demand = JSONObject.parseObject(pageSize.getCondition(), Demand.class);
                if (demand.getShopperId() != null) {
                    condition.append(" and shopperId =?");
                    list.add(demand.getShopperId());
                }
                if (demand.getReleaseDateStart() != null) {
                    condition.append(" and releaseDate >=?");
                    list.add(demand.getReleaseDateStart());
                }
                if (demand.getReleaseDateEnd() != null) {
                    condition.append(" and releaseDate <=?");
                    list.add(demand.getReleaseDateEnd());
                }
                if (demand.getContacts() != null) {
                    condition.append(" and contacts =?");
                    list.add(demand.getContacts());
                }
                if (demand.getProductName() != null) {
                    condition.append(" and productName =?");
                    list.add(demand.getProductName());
                }
                if (demand.getBuyoutPriceStart() != null) {
                    condition.append(" and buyoutPrice >=?");
                    list.add(demand.getBuyoutPriceStart());
                }
                if (demand.getBuyoutPriceEnd() != null) {
                    condition.append(" and buyoutPrice <=?");
                    list.add(demand.getBuyoutPriceEnd());
                }
                if (demand.getMaxPriceStart() != null) {
                    condition.append(" and maxPrice >=?");
                    list.add(demand.getMaxPriceStart());
                }
                if (demand.getMaxPriceStart() != null) {
                    condition.append(" and maxPrice >=?");
                    list.add(demand.getMaxPriceStart());
                }
                if (demand.getStatus() != null) {
                    condition.append(" and status =?");
                    list.add(demand.getStatus());
                }
                if (demand.getPriceType() != null) {//查询价格类型
                    if (demand.getPriceType() == 0) {
                        condition.append(" and buyoutPrice is not null");
                    } else {
                        condition.append(" and maxPrice is not null");
                    }
                }
            }
            condition.append(" and demandId not in (:demandIdList)");


            if (pageSize.getMemberId() != null) {//查询交易中的订单 排除卖家交易中供应信息
                Query query = this.getSession().createQuery("from Transaction where deFlag = 0 and memberId = :memberId and status = 1");
                query.setParameter("memberId", pageSize.getMemberId());
                List<Transaction> transactionList = query.list();


                if (transactionList.size() > 0) {

                    for (Transaction transaction : transactionList) {
                        if (transaction.getDemandId() != null) {
                            demandIdList.add(transaction.getDemandId());
                        }
                    }
                }
            }
            condition.append(" and endTime >= :nowDate");
            StringBuilder listhql = new StringBuilder("from Demand where  " + condition +" order by timestamp desc");

            Query query = this.getSession().createQuery(listhql.toString());
            setQueryParameters(query, list);
            query.setParameter("nowDate",new Date());


            query.setParameter("demandIdList", demandIdList);


            List<Demand> transactionList = query.list();
            PageSize pageSize1 = new PageSize();
            pageSize1.setList(transactionList);
            return pageSize1;

        } else {
            List<Integer> demandIdList = new ArrayList<>();
            demandIdList.add(0);
            List<Object> list = new ArrayList<Object>();
            PageSize ps = new PageSize();
            ps.setPage(pageSize.getPage());
            Integer start = (pageSize.getPage() - 1) * pageSize.getSize();


            StringBuilder condition = new StringBuilder("  deFlag =0 ");
            if (pageSize.getCondition() != null) {
                Demand demand = JSONObject.parseObject(pageSize.getCondition(), Demand.class);
                if (demand.getShopperId() != null) {
                    condition.append(" and shopperId =?");
                    list.add(demand.getShopperId());
                }
                if (demand.getReleaseDateStart() != null) {
                    condition.append(" and releaseDate >=?");
                    list.add(demand.getReleaseDateStart());
                }
                if (demand.getReleaseDateEnd() != null) {
                    condition.append(" and releaseDate <=?");
                    list.add(demand.getReleaseDateEnd());
                }
                if (demand.getContacts() != null) {
                    condition.append(" and contacts =?");
                    list.add(demand.getContacts());
                }
                if (demand.getProductName() != null) {
                    condition.append(" and productName =?");
                    list.add(demand.getProductName());
                }
                if (demand.getBuyoutPriceStart() != null) {
                    condition.append(" and buyoutPrice >=?");
                    list.add(demand.getBuyoutPriceStart());
                }
                if (demand.getBuyoutPriceEnd() != null) {
                    condition.append(" and buyoutPrice <=?");
                    list.add(demand.getBuyoutPriceEnd());
                }
                if (demand.getMaxPriceStart() != null) {
                    condition.append(" and maxPrice >=?");
                    list.add(demand.getMaxPriceStart());
                }
                if (demand.getMaxPriceEnd() != null) {
                    condition.append(" and maxPrice >=?");
                    list.add(demand.getMaxPriceEnd());
                }
                if (demand.getStatus() != null) {
                    condition.append(" and status =?");
                    list.add(demand.getStatus());
                }
                if (demand.getPriceType() != null) {
                    if (demand.getPriceType() == 0) {
                        condition.append(" and buyoutPrice is not null");
                    } else {
                        condition.append(" and maxPrice is not null");
                    }
                }
            }

            condition.append(" and demandId not in (:demandIdList)");

            if (pageSize.getMemberId() != null) {//查询memberId所在交易信息
                Query query = this.getSession().createQuery("from Transaction where deFlag = 0 and memberId = :memberId and state = 1");
                query.setParameter("memberId", pageSize.getMemberId());
                List<Transaction> transactionList = query.list();


                if (transactionList.size() > 0) {

                    for (Transaction transaction : transactionList) {
                        if (transaction.getDemandId() != null) {
                            demandIdList.add(transaction.getDemandId());
                        }
                    }
                }
            }

            condition.append(" and endTime >= :nowDate");
            StringBuilder counthql = new StringBuilder("select count(*) from Demand where  " + condition +" order by timestamp desc");
            StringBuilder listhql = new StringBuilder("from Demand where  " + condition +" order by timestamp desc");

            Query queryTotal = this.getSession().createQuery(counthql.toString());
            setQueryParameters(queryTotal, list);
            queryTotal.setParameter("nowDate",new Date());

            queryTotal.setParameter("demandIdList", demandIdList);

            ps.setTotal((Long) queryTotal.uniqueResult());

            Query query = this.getSession().createQuery(listhql.toString());
            setQueryParameters(query, list);
            query.setParameter("nowDate", new Date());

            query.setParameter("demandIdList", demandIdList);

            query.setFirstResult(start);
            query.setMaxResults(pageSize.getSize());

            List<Demand> demandList = query.list();
            if (demandList == null || demandList.size() == 0) {
                ps.setPage(1);
                Query queryFirst = this.getSession().createQuery(listhql.toString());
                setQueryParameters(queryFirst, list);
                queryFirst.setParameter("nowDate", new Date());
                queryFirst.setParameter("demandIdList", demandIdList);

                queryFirst.setFirstResult(0);
                queryFirst.setMaxResults(pageSize.getSize());
                demandList = queryFirst.list();
            }
            ps.setList(demandList);
            return ps;
        }

    }

    @Override
    public List<Demand> queryListToday() {


        Query query = this.getSession().createQuery("from Demand where deFlag = 0 and endTime >= :nowDate and status = 0");
        query.setParameter("nowDate", new Date());

        return query.list();
    }
}
