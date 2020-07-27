package com.senontech.dao;



import com.senontech.entity.FuzzyQuery;
import com.senontech.entity.Member;

import java.util.List;

public interface IMemberDao  extends IBaseDao{
    /**
     * 添加区域数据
     * @param param
     * @return
     */
    void add(Member param);

    /**
     * 删除区域数据
     * @param memberId
     * @return
     */
    void del(Integer memberId);
    void delList(List<Integer> memberIdList);

    /**
     * 修改区域数据
     * @param param
     * @return
     */
    void edit(Member param);

    /**
     * 单个查询区域数据
     * @param param
     * @return
     */
    Member query(Member param);

    /**
     * 查询区域数据列表
     * @return
     */
    List<Member> queryList();

    /**
     * 根据拼音排序
     * @return
     */
    List<Member> queryListByCPY();

    /**
     * 根据区域排序
     * @return
     */
    List<Member> queryMemberListByArea();

    /**
     * 根据Id列表查询会员
     * @return
     */
    List<Member> getMemberList(List<Integer> idList);

    /**
     * 按条件模糊查询并按拼音排序
     * @return
     */
    List<Member> queryFuzzyListByCPY(FuzzyQuery param);

    /**
     * 按名称模糊查询并按区域排序
     * @return
     */
    List<Member> queryFuzzyListByArea(FuzzyQuery param);

    /**
     * 按产品或上市时间模糊查询并按拼音排序
     * @return
     */
    List<Member> queryFuzzyListByMarketTimeCPY(FuzzyQuery param);

    /**
     * 按产品或上市时间模糊查询并按区域排序
     * @return
     */
    List<Member> queryFuzzyListByMarketTimeArea(FuzzyQuery param);

    /**
     * 区域验证
     * @return
     */
    Boolean checkRepeat(Member param);

    /**
     * 用户登录接口
     * @return
     */
    Member userLogin(Member param);

    List<String> queryWechatId();
    /**
     * 修改用户数据
     * @param param
     * @return
     */
    void unbind(Member param);
}
