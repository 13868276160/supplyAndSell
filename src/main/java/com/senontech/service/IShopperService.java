package com.senontech.service;




import com.senontech.entity.Shopper;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;

import java.util.List;

public interface IShopperService {
    /**
     * 添加买家数据
     * @param param
     * @return
     */
    void add(Shopper param) throws ErrorCodeException;

    /**
     * 删除买家数据
     * @param shopperId
     * @return
     */
    void del(Integer shopperId);
    void delList(List<Integer> shopperIdList);

    /**
     * 修改买家数据
     * @param param
     * @return
     */
    void edit(Shopper param) throws ErrorCodeException;

    /**
     * 单个查询买家数据
     * @param param
     * @return
     */
    Shopper query(Shopper param);

    /**
     * 查询买家数据列表
     * @return
     */
    List<Shopper> queryList();
    /**
     * 检验添加买家是否重复
     * @return
     */
    Boolean checkRepeat(Shopper param);

    /**
     * 买家登录接口
     * @return
     */
    Shopper shopperLogin(Shopper param);
    /**
     * 分页查询列表
     *
     * @param pageSize
     * @return
     */

    PageSize queryPageList(PageSize pageSize) throws ErrorCodeException;
}
