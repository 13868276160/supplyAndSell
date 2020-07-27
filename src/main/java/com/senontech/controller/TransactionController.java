package com.senontech.controller;

import com.alibaba.fastjson.JSON;
import com.senontech.entity.Transaction;
import com.senontech.param.PageSize;
import com.senontech.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@Controller
@RequestMapping(value = "/transaction", produces = "application/json;charset=UTF-8")
public class TransactionController extends AbstractController{
    @Autowired
    private ITransactionService transactionService;

    /**
     * 交易信息添加
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Transaction param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            transactionService.add(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "交易信息添加成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息添加异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 交易信息删除
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestBody Transaction param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            transactionService.del(param.getSupplyId());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "交易信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    @ResponseBody
    @RequestMapping(value = "/delList", method = RequestMethod.POST)
    public String delList(@RequestBody Transaction param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            transactionService.delList(param.getTransactionIdList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "交易信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 交易信息修改
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Transaction param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            transactionService.edit(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "交易信息修改成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息修改异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 单个查询交易信息数据
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(@RequestBody Transaction param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",transactionService.query(param));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "交易信息查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询交易信息数据列表
     *
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public String queryList(@RequestBody Transaction param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",transactionService.queryList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "交易信息列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 分页查询列表
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryPageList", method = RequestMethod.POST)
    public String queryByLogParam(@RequestBody PageSize pageSize) {
        Map<String, Object> responseBody = new HashMap();
        responseBody.put(CODE, 1);
        try {
            responseBody.put(DATA, transactionService.queryPageList(pageSize));
            responseBody.put(CODE, 0);
            responseBody.put("msg", "交易信息分页查询成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息分页查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 大屏查询列表
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryListToday", method = RequestMethod.GET)
    public String queryListToday() {
        Map<String, Object> responseBody = new HashMap();
        responseBody.put(CODE, 1);
        try {
            responseBody.put(DATA, transactionService.queryListToday());
            responseBody.put(CODE, 0);
            responseBody.put("msg", "交易信息分页查询成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "交易信息分页查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

}
