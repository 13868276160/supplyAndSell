package com.senontech.dao;



import com.senontech.entity.MarketTime;

import java.util.List;

public interface IMarketTimeDao extends IBaseDao{
    /**
     * 添加上市时间数据
     * @param param
     * @return
     */
    void add(MarketTime param);

    /**
     * 删除上市时间数据
     * @param marketTimeId
     * @return
     */
    void del(Integer marketTimeId);
    void delList(List<Integer> marketTimeIdList);

    /**
     * 修改上市时间数据
     * @param param
     * @return
     */
    void edit(MarketTime param);

    /**
     * 单个查询上市时间数据
     * @param param
     * @return
     */
    MarketTime query(MarketTime param);

    /**
     * 查询上市时间数据列表
     * @return
     */
    List<MarketTime> queryList(Integer memberId);

    /**
     * 查询产品名称列表
     * @return
     */
    List<String> queryProductNameList();

    /**
     * 查询产品名称查询用户id列表
     * @return
     */
    List<Integer> queryMemberIdList(String productName);

    /**
     * 上市时间验证
     * @return
     */
    Boolean checkRepeat(MarketTime param);
}
