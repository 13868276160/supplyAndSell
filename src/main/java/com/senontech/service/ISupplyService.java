package com.senontech.service;




import com.senontech.entity.Supply;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;

import java.text.ParseException;
import java.util.List;

public interface ISupplyService {

    /**
     * 添加供应数据
     * @param param
     * @return
     */
    void add(Supply param) ;

    /**
     * 删除供应数据
     * @param demandId
     * @return
     */
    void del(Integer demandId) ;
    void delList(List<Integer> demandIdList);

    /**
     * 修改供应数据
     * @param param
     * @return
     */
    void edit(Supply param) ;

    /**
     * 根据供应ID查询需求信息
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
     * 大屏查询供应数据列表
     * @return
     */
    List<Supply> queryListToday() ;

}
