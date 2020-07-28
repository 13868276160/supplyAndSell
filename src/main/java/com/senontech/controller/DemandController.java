package com.senontech.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.senontech.entity.Demand;
import com.senontech.entity.Supply;
import com.senontech.param.PageSize;
import com.senontech.service.IDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(value = "/demand", produces = "application/json;charset=UTF-8")
public class DemandController extends AbstractController{

    @Autowired
    private IDemandService demandService;

    /**
     * 需求信息添加
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Demand param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            demandService.add(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "需求信息添加成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "需求信息添加异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 销售客户删除
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestBody Demand param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            demandService.del(param.getDemandId());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "需求信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "需求信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    @ResponseBody
    @RequestMapping(value = "/delList", method = RequestMethod.POST)
    public String delList(@RequestBody Demand param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            demandService.delList(param.getDemandIdList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "需求信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "需求信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 需求信息修改
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Demand param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            demandService.edit(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "需求信息修改成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "需求信息修改异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 单个查询需求信息数据
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(@RequestBody Demand param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",demandService.query(param));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "需求信息查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "需求信息查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询需求信息数据列表
     *
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public String queryList(@RequestBody Demand param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",demandService.queryList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "需求信息列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "需求信息列表查询异常。");
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
            responseBody.put(DATA, demandService.queryPageList(pageSize));
            responseBody.put(CODE, 0);
            responseBody.put("msg", "需求信息分页查询成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "需求信息分页查询异常。");
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
    @RequestMapping(value = "/queryScreen", method = RequestMethod.GET)
    public String queryListToday() {
        List<Demand> demandList = new ArrayList<>();
        try {
            demandList= demandService.queryListToday();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONArray.toJSONString(demandList);
    }
}
