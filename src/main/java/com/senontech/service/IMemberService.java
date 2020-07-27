package com.senontech.service;



import com.senontech.entity.FuzzyQuery;
import com.senontech.entity.Member;
import com.senontech.entity.ProductMember;
import com.senontech.exceptions.ErrorCodeException;

import java.util.List;

public interface IMemberService {
    /**
     * 添加Member数据
     * @param param
     * @return
     */
    void add(Member param) throws Exception;

    /**
     * 删除Member数据
     * @param memberId
     * @return
     */
    void del(Integer memberId) throws Exception;
    void delList(List<Integer> memberIdList);

    /**
     * 修改Member数据
     * @param param
     * @return
     */
    void edit(Member param) throws ErrorCodeException;

    /**
     * 根据设备ID查询Member信息
     * @return
     */
    Member query(Member param);

    /**
     * 查询Member数据列表
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
     * 根据产品排序
     * @return
     */
    List<ProductMember> queryMemberListByProduct();
    /**
     * 根据区域排序
     * @return
     */
    List<Member> queryFuzzyList(FuzzyQuery param);
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
