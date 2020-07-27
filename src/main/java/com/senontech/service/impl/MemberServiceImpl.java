package com.senontech.service.impl;


import com.senontech.dao.IMarketTimeDao;
import com.senontech.dao.IMemberDao;
import com.senontech.entity.FuzzyQuery;
import com.senontech.entity.Member;
import com.senontech.entity.ProductMember;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private IMemberDao memberDao;
    @Autowired
    private IMarketTimeDao marketTimeDao;

    /**
     * 添加会员数据
     *
     * @param param
     * @return
     */
    @Override
    public void add(Member param) throws ErrorCodeException {
//        if(this.memberDao.checkRepeat(param)){
//            throw  new ErrorCodeException(2);
//        }

        param.setDeFlag(0);
        param.setTimestamp(new Date());
        memberDao.add(param);
    }

    /**
     * 删除会员数据
     * @param memberId
     * @return
     */
    @Override
    public void del(Integer memberId) {
        this.memberDao.del(memberId);
    }
    @Override
    public void delList(List<Integer> memberIdList) {
        this.memberDao.delList(memberIdList);
    }

    /**
     * 修改会员数据
     * @param param
     * @return
     */
    @Override
    public void edit(Member param) throws ErrorCodeException {
//        if(this.memberDao.checkRepeat(param)){
//            throw  new ErrorCodeException(2);
//        }
        this.memberDao.edit(param);
    }

    /**
     * 单个查询会员数据
     *
     * @param param
     * @return
     */
    @Override
    public Member query(Member param) {
        return this.memberDao.query(param);
    }

    /**
     * 查询会员数据列表
     *
     * @return
     */
    @Override
    public List<Member> queryList() {
        List<Member> arr = this.memberDao.queryList();
        return arr;
    }

    @Override
    public List<Member> queryListByCPY(){
        List<Member> arr = this.memberDao.queryListByCPY();
        return arr;
    }

    @Override
    public List<Member> queryMemberListByArea(){
        List<Member> arr = this.memberDao.queryMemberListByArea();
        return arr;
    }

    @Override
    public List<ProductMember> queryMemberListByProduct(){
        List<ProductMember> arr = new ArrayList<ProductMember>();
        // 查询品名，不重复
        List<String> productNameList= this.marketTimeDao.queryProductNameList();
        for(String name:productNameList){
            // 根据品名查询会员id列表
            List<Integer> idList = this.marketTimeDao.queryMemberIdList(name);
            List<Member> memberList = this.memberDao.getMemberList(idList);
            ProductMember pm = new ProductMember();
            pm.setProductName(name);
            pm.setMemberList(memberList);
            arr.add(pm);
        }
        return  arr;
//        List<Member> arr = this.memberDao.queryMemberListByProduct();
//        return arr;
    }

    @Override
    public List<Member> queryFuzzyList(FuzzyQuery param){
        if( param.getQueryType() == 0 ){
            if(param.getOrderType() == 1){
                List<Member> arr = this.memberDao.queryFuzzyListByCPY(param);
                return arr;
            }else{
                List<Member> arr = this.memberDao.queryFuzzyListByArea(param);
                return arr;
            }
        }else{
            if(param.getOrderType() == 1){
                List<Member> arr = this.memberDao.queryFuzzyListByMarketTimeCPY(param);
                return arr;
            }else{
                List<Member> arr = this.memberDao.queryFuzzyListByMarketTimeArea(param);
                return arr;
            }
        }
    }

    /**
     * 用户登录接口
     *
     * @param param
     * @return
     */
    @Override
    public Member userLogin(Member param) {
        Member member = this.memberDao.userLogin(param);
        return member;
    }

    @Override
    public List<String> queryWechatId() {
        return this.memberDao.queryWechatId();
    }

    /**
     * 修改用户数据
     *
     * @param param
     * @return
     */
    @Override
    public void unbind(Member param) {
        this.memberDao.unbind(param);
    }
}




