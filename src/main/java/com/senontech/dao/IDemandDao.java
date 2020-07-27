package com.senontech.dao;



import com.senontech.entity.Demand;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;

import java.text.ParseException;
import java.util.List;

public interface IDemandDao {
    /**
     * 添加需求数据
     * @param param
     * @return
     */
    void add(Demand param) throws ErrorCodeException;

    /**
     * 删除需求数据
     * @param demandId
     * @return
     */
    void del(Integer demandId);
    /**
     * 批量删除需求数据
     * @param demandIdList
     * @return
     */
    void delList(List<Integer> demandIdList);

    /**
     * 修改需求数据
     * @param param
     * @return
     */
    void edit(Demand param);

    /**
     * 单个查询需求数据
     * @param param
     * @return
     */
    Demand query(Demand param);

    /**
     * 查询需求数据列表
     * @return
     */
    List<Demand> queryList();

    /**
     * 分页查询列表
     *
     * @param pageSize
     * @return
     */

    PageSize queryPageList(PageSize pageSize) throws ErrorCodeException, ParseException;

    /**
     * 大屏查询需求数据列表
     * @return
     */
    List<Demand> queryListToday() throws ParseException;
}
