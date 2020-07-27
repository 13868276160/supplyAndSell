package com.senontech.dao;



import com.senontech.entity.Transaction;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;

import java.text.ParseException;
import java.util.List;

public interface ITransactionDao {
    /**
     * 添加交易数据
     * @param param
     * @return
     */
    void add(Transaction param) throws ErrorCodeException;

    /**
     * 删除交易数据
     * @param transactionId
     * @return
     */
    void del(Integer transactionId);
    /**
     * 批量删除交易数据
     * @param transactionIdList
     * @return
     */
    void delList(List<Integer> transactionIdList);

    /**
     * 修改交易数据
     * @param param
     * @return
     */
    void edit(Transaction param);

    /**
     * 单个查询交易数据
     * @param param
     * @return
     */
    Transaction query(Transaction param);

    /**
     * 查询交易数据列表
     * @return
     */
    List<Transaction> queryList();
    /**
     * 分页查询列表
     *
     * @param pageSize
     * @return
     */

    PageSize queryPageList(PageSize pageSize) throws ErrorCodeException;

    /**
     * 大屏查询需求数据列表
     * @return
     */
    List<Transaction> queryListToday() throws ParseException;
}
