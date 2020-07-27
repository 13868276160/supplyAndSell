package com.senontech.controller;

import com.alibaba.fastjson.JSON;

import com.senontech.entity.Supply;
import com.senontech.param.PageSize;
import com.senontech.service.ISupplyService;
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
@RequestMapping(value = "/supply", produces = "application/json;charset=UTF-8")
public class SupperController extends AbstractController{
    @Autowired
    private ISupplyService supplyService;

    /**
     * 供应信息添加
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Supply param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            supplyService.add(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "供应信息添加成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息添加异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 供应信息删除
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestBody Supply param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            supplyService.del(param.getSupplyId());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "供应信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    @ResponseBody
    @RequestMapping(value = "/delList", method = RequestMethod.POST)
    public String delList(@RequestBody Supply param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            supplyService.delList(param.getSupplyIdList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "供应信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 供应信息修改
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Supply param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            supplyService.edit(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "供应信息修改成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息修改异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 单个查询供应信息数据
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(@RequestBody Supply param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",supplyService.query(param));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "供应信息查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询供应信息数据列表
     *
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public String queryList(@RequestBody Supply param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",supplyService.queryList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "供应信息列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息列表查询异常。");
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
            responseBody.put(DATA, supplyService.queryPageList(pageSize));
            responseBody.put(CODE, 0);
            responseBody.put("msg", "供应信息分页查询成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息分页查询异常。");
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
            responseBody.put(DATA, supplyService.queryListToday());
            responseBody.put(CODE, 0);
            responseBody.put("msg", "供应信息分页查询成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "供应信息分页查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

}
