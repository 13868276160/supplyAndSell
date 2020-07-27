package com.senontech.service.impl;


import com.senontech.dao.ITransactionDao;
import com.senontech.entity.Transaction;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import com.senontech.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {


    @Autowired
    private ITransactionDao transactionDao;

    @Override
    public void add(Transaction param) throws Exception {
        if(param.getTransactionPrice()!=null&&param.getValue()!=null){
            BigDecimal transactionPrice=new BigDecimal(param.getTransactionPrice());
            BigDecimal value=new BigDecimal(param.getValue());
            param.setTransactionAmount(value.multiply(transactionPrice).doubleValue());

        }
        this.transactionDao.add(param);
    }

    @Override
    public void del(Integer transactionId) throws Exception {
        this.transactionDao.del(transactionId);

    }

    @Override
    public void delList(List<Integer> transactionIdList) {
        this.transactionDao.delList(transactionIdList);

    }

    @Override
    public void edit(Transaction param) throws ErrorCodeException {
        this.transactionDao.edit(param);

    }

    @Override
    public Transaction query(Transaction param) {
        return this.transactionDao.query(param);
    }

    @Override
    public List<Transaction> queryList() {
        return this.transactionDao.queryList();
    }

    @Override
    public PageSize queryPageList(PageSize pageSize) throws ErrorCodeException {
        return this.transactionDao.queryPageList(pageSize);
    }

    @Override
    public List<Transaction> queryListToday() throws ParseException {
        return this.transactionDao.queryListToday();
    }
}
