package com.senontech.service;




import com.senontech.entity.Transaction;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;

import java.text.ParseException;
import java.util.List;

public interface ITransactionService {
    /**
     * 添加交易数据
     * @param param
     * @return
     */
    void add(Transaction param) throws Exception;

    /**
     * 删除交易数据
     * @param transactionId
     * @return
     */
    void del(Integer transactionId) throws Exception;
    void delList(List<Integer> transactionIdList);

    /**
     * 修改交易数据
     * @param param
     * @return
     */
    void edit(Transaction param) throws ErrorCodeException;

    /**
     * 根据交易ID查询交易信息
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
     * 大屏查询交易数据列表
     * @return
     */
    List<Transaction> queryListToday() throws ParseException;
}
