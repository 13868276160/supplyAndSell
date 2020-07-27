package com.senontech.dao.impl;


import com.senontech.dao.IMemberDao;
import com.senontech.entity.FuzzyQuery;
import com.senontech.entity.Member;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MemberDaoImpl extends BaseDaoImpl implements IMemberDao {
    /**
     * 添加区域数据
     *
     * @param param
     * @return
     */
    @Override
    public void add(Member param) {
        param.setDeFlag(0);
        param.setTimestamp(new Date());
        getSession().save(param);
        //this.save(param);
    }

    /**
     * 删除区域数据
     *
     * @param memberId
     * @return
     */
    @Override
    public void del(Integer memberId) {
        Member member = this.getByDeFlag(Member.class, memberId);
        this.delete(member);
    }

    @Override
    public void delList(List<Integer> memberIdList) {
        Query query = this.getSession().createQuery("UPDATE Member set deFlag = 1 where memberId in (:memberIdList) and deFlag =0");
        query.setParameter("memberIdList", memberIdList);
        query.executeUpdate();
    }

    /**
     * 修改区域数据
     *
     * @param param
     * @return
     */
    @Override
    public void edit(Member param) {
        Member member = this.getByDeFlag(Member.class, param.getMemberId());
        if (param.getPassword() != null)
            member.setPassword(param.getPassword());
        if (param.getMemberName() != null)
            member.setMemberName(param.getMemberName());
        if (param.getAreaId() != null)
            member.setAreaId(param.getAreaId());
        if (param.getIsMember() != null)
            member.setIsMember(param.getIsMember());
        if (param.getChargeMan() != null)
            member.setChargeMan(param.getChargeMan());
        if (param.getPhone() != null)
            member.setPhone(param.getPhone());
        if (param.getAddress() != null)
            member.setAddress(param.getAddress());
        if (param.getScale() != null)
            member.setScale(param.getScale());
        if (param.getLng() != null)
            member.setLng(param.getLng());
        if (param.getLat() != null)
            member.setLat(param.getLat());
        if (param.getWechatId() != null)
            member.setWechatId(param.getWechatId());
        member.setTimestamp(new Date());
        this.update(member);
    }

    /**
     * 单个查询区域数据
     *
     * @param param
     * @return
     */
    @Override
    public Member query(Member param) {
        Member member = this.getByDeFlag(Member.class, param.getMemberId());
        return member;
    }

    /**
     * 查询区域数据列表
     *
     * @return
     */
    @Override
    public List<Member> queryList() {
        Query query = this.getSession().createQuery(" from Member where deFlag = 0");
        return query.list();
    }

    @Override
    public List<Member> queryListByCPY() {
        StringBuilder sb = new StringBuilder("select * from t_member m where deFlag = 0 order by m.MEMBERNAME collate Chinese_PRC_CS_AS_KS_WS");
        NativeQuery query = this.getSession().createNativeQuery(sb.toString());
        query.addEntity(Member.class);
        return query.list();
    }

    @Override
    public List<Member> queryMemberListByArea() {
        Query query = this.getSession().createQuery(" from Member where deFlag = 0 order by areaId");
        return query.list();
    }

    @Override
    public List<Member> getMemberList(List<Integer> idList) {
        Query query = this.getSession().createQuery(" from Member where deFlag = 0 and memberId in (:idList)");
        query.setParameter("idList", idList);
        return query.list();
    }

    /**
     * 根据名称模糊查询按拼音排序
     *
     * @param param
     * @return
     */
    @Override
    public List<Member> queryFuzzyListByCPY(FuzzyQuery param) {
        StringBuilder sb = new StringBuilder("select * from t_member m where deFlag = 0");
        if (param.getQueryType() == 0 && param.getQueryFactor() != null) {
            sb.append(" and m.MEMBERNAME like :memberName");
        }
        sb.append(" order by m.MEMBERNAME collate Chinese_PRC_CS_AS_KS_WS");
        NativeQuery query = this.getSession().createNativeQuery(sb.toString());
        if (param.getQueryFactor() != null) {
            query.setParameter("memberName", "%" + param.getQueryFactor() + "%");
        }
        query.addEntity(Member.class);
        return query.list();
    }


    /**
     * 根据名称模糊查询按区域排序
     *
     * @param param
     * @return
     */
    @Override
    public List<Member> queryFuzzyListByArea(FuzzyQuery param) {
        StringBuilder sb = new StringBuilder("select * from t_member m where deFlag = 0");
        if (param.getQueryType() == 0 && param.getQueryFactor() != null) {
            sb.append(" and m.MEMBERNAME like :memberName");
        }
        sb.append(" order by areaId");
        NativeQuery query = this.getSession().createNativeQuery(sb.toString());
        if (param.getQueryFactor() != null) {
            query.setParameter("memberName", "%" + param.getQueryFactor() + "%");
        }
        query.addEntity(Member.class);
        return query.list();
    }

    /**
     * 根据产品或上市时间查询按拼音排序
     *
     * @param param
     * @return
     */
    @Override
    public List<Member> queryFuzzyListByMarketTimeCPY(FuzzyQuery param) {
        List<Integer> list = queryIdList(param);
        if (list.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder("select * from t_member m where deFlag = 0 and m.MEMBERID in (:memberIdList) order by m.MEMBERNAME collate Chinese_PRC_CS_AS_KS_WS");
        NativeQuery query = this.getSession().createNativeQuery(sb.toString());
        query.setParameter("memberIdList", list);
        query.addEntity(Member.class);
        return query.list();
    }

    /**
     * 根据产品或上市时间查询按区域排序
     *
     * @param param
     * @return
     */
    @Override
    public List<Member> queryFuzzyListByMarketTimeArea(FuzzyQuery param) {
        List<Integer> list = queryIdList(param);
        if (list.isEmpty()) {
            return null;
        }
        Query query = this.getSession().createQuery(" from Member where memberId in (:memberIdList) and deFlag = 0 order by areaId");
        query.setParameter("memberIdList", list);
        return query.list();
    }

    // 根据条件模糊查询去重的id列表
    public List<Integer> queryIdList(FuzzyQuery param) {
        StringBuilder sb = new StringBuilder("select distinct m.memberId from MarketTime m where m.deFlag = 0");

        if (param.getQueryType() == 1 && param.getQueryFactor() != null) {
            sb.append(" and m.productName like :queryFactor");
        } else if (param.getQueryType() == 2 && param.getQueryFactor() != null) {
            sb.append(" and m.marketTime like :queryFactor");
        }

        Query query = this.getSession().createQuery(sb.toString());

        if (param.getQueryFactor() != null) {
            query.setParameter("queryFactor", "%" + param.getQueryFactor() + "%");
        }

        return query.list();
    }


    /**
     * 区域验证
     *
     * @param param
     * @return
     */
    @Override
    public Boolean checkRepeat(Member param) {
        List<Object> list = new ArrayList<Object>();
        StringBuilder sb = new StringBuilder("select count(*) from Member  where memberName = ? and deFlag = 0");
        list.add(param.getMemberName());
        if (param.getMemberId() != null) {
            sb.append(" and memberId != ?");
            list.add(param.getMemberId());
        }
        Query query = this.getSession().createQuery(sb.toString());
        setQueryParameters(query, list);
        return (Long) query.uniqueResult() > 0;
    }

    /**
     * 用户登录接口
     *
     * @param param
     * @return
     */
    @Override
    public Member userLogin(Member param) {
        if (param.getWechatId() != null) {//微信登录
            Query query = this.getSession().createQuery(" from Member where wechatId = :wechatId  and deFlag =0");
            query.setParameter("wechatId", param.getWechatId());
            query.setMaxResults(1);
            Member member = (Member) query.uniqueResult();
            return member;
        } else {//用户登录
            Query query = this.getSession().createQuery(" from Member where account = :account and password = :password and deFlag =0");
            query.setParameter("account", param.getAccount()).setParameter("password", param.getPassword());
            query.setMaxResults(1);
            Member member = (Member) query.uniqueResult();
//        if(user!=null){
//            Query query1 = this.getSession().createQuery("select  r from Role r,UserRole  ur where ur.roleId = r.roleId and ur.userId = :userId and r.deFlag = 0 and  ur.deFlag =0");
//            query1.setParameter("userId",user.getUserId());
//            query1.setMaxResults(1);
//            user.setRole((Role) query1.uniqueResult());
//        }
            return member;
        }
    }

    @Override
    public List<String> queryWechatId() {
        Query query = this.getSession().createQuery("select wechatId from Member where wechatId is not null");
        return query.list();
    }

    /**
     * 修改用户数据
     *
     * @param param
     * @return
     */
    @Override
    public void unbind(Member param) {
        Member member = this.getByDeFlag(Member.class, param.getMemberId());
        member.setWechatId(param.getWechatId());
        this.update(member);
    }
}
