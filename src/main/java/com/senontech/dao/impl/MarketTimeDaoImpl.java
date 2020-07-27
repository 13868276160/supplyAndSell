package com.senontech.dao.impl;


import com.senontech.dao.IMarketTimeDao;
import com.senontech.entity.MarketTime;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MarketTimeDaoImpl extends BaseDaoImpl implements IMarketTimeDao {
    /**
     * 添加上市时间数据
     *
     * @param param
     * @return
     */
    @Override
    public void add(MarketTime param) {
        param.setDeFlag(0);
        param.setTimestamp(new Date());
        this.save(param);
    }

    /**
     * 删除上市时间数据
     *
     * @param marketTimeId
     * @return
     */
    @Override
    public void del(Integer marketTimeId) {
        MarketTime marketTime = this.getByDeFlag(MarketTime.class, marketTimeId);
        this.delete(marketTime);
    }

    @Override
    public void delList(List<Integer> marketTimeIdList) {
        Query query = this.getSession().createQuery("UPDATE MarketTime set deFlag = 1 where marketTimeId in (:marketTimeIdList) and deFlag =0");
        query.setParameter("marketTimeIdList", marketTimeIdList);
        query.executeUpdate();
    }

    /**
     * 修改上市时间数据
     *
     * @param param
     * @return
     */
    @Override
    public void edit(MarketTime param) {
        MarketTime marketTime = this.getByDeFlag(MarketTime.class, param.getMarketTimeId());
        if (param.getMarketTime() != null)
            marketTime.setMarketTime(param.getMarketTime());
        this.update(marketTime);
    }

    /**
     * 单个查询上市时间数据
     *
     * @param param
     * @return
     */
    @Override
    public MarketTime query(MarketTime param) {
        MarketTime marketTime = this.getByDeFlag(MarketTime.class, param.getMarketTimeId());
        return marketTime;
    }

    /**
     * 查询上市时间数据列表
     *
     * @return
     */
    @Override
    public List<MarketTime> queryList(Integer memberId) {
        Query query = this.getSession().createQuery("from MarketTime where memberId = :memberId and deFlag = 0");
        query.setParameter("memberId", memberId);
        return query.list();
    }

    /**
     * 查询产品名称列表
     *
     * @return
     */
    @Override
    public List<String> queryProductNameList() {
        Query query = this.getSession().createQuery("select distinct productName from MarketTime where deFlag = 0");
        return query.list();
    }

    /**
     * 查询产品名称列表
     *
     * @return
     */
    @Override
    public List<Integer> queryMemberIdList(String productName) {
        Query query = this.getSession().createQuery("select memberId from MarketTime where productName=:productName and deFlag = 0");
        query.setParameter("productName", productName);
        return query.list();
    }

    /**
     * 上市时间验证
     *
     * @param param
     * @return
     */
    @Override
    public Boolean checkRepeat(MarketTime param) {
        List<Object> list = new ArrayList<Object>();
        StringBuilder sb = new StringBuilder("select count(*) from MarketTime  where marketTime = ? and deFlag = 0 and memberId = ?");
        list.add(param.getMarketTime());
        list.add(param.getMemberId());
        if (param.getMarketTimeId() != null) {
            sb.append(" and marketTimeId != ?");
            list.add(param.getMarketTimeId());
        }
        Query query = this.getSession().createQuery(sb.toString());
        setQueryParameters(query, list);
        return (Long) query.uniqueResult() > 0;
    }
}
