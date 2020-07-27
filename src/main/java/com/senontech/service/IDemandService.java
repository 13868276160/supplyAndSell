package com.senontech.service;




import com.senontech.entity.Demand;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;

import java.text.ParseException;
import java.util.List;

public interface IDemandService {
    /**
     * 添加需求数据
     * @param param
     * @return
     */
    void add(Demand param) throws Exception;

    /**
     * 删除需求数据
     * @param demandId
     * @return
     */
    void del(Integer demandId) throws Exception;
    void delList(List<Integer> demandIdList);

    /**
     * 修改需求数据
     * @param param
     * @return
     */
    void edit(Demand param) throws ErrorCodeException;

    /**
     * 根据需求ID查询需求信息
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
