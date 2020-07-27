package com.senontech.dao;



import com.senontech.entity.Supply;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;

import java.text.ParseException;
import java.util.List;

public interface ISupplyDao {
    /**
     * 添加供应数据
     * @param param
     * @return
     */
    void add(Supply param) ;

    /**
     * 删除供应数据
     * @param supplyId
     * @return
     */
    void del(Integer supplyId);
    /**
     * 批量删除供应数据
     * @param supplyIdList
     * @return
     */
    void delList(List<Integer> supplyIdList);

    /**
     * 修改供应数据
     * @param param
     * @return
     */
    void edit(Supply param);

    /**
     * 单个查询供应数据
     * @param param
     * @return
     */
    Supply query(Supply param);

    /**
     * 查询供应数据列表
     * @return
     */
    List<Supply> queryList();
    /**
     * 分页查询列表
     *
     * @param pageSize
     * @return
     */

    PageSize queryPageList(PageSize pageSize) ;

    /**
     * 大屏查询需求数据列表
     * @return
     */
    List<Supply> queryListToday() ;
}
