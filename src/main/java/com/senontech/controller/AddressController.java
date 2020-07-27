package com.senontech.controller;

import com.alibaba.fastjson.JSON;
import com.senontech.entity.Address;
import com.senontech.entity.Demand;
import com.senontech.param.PageSize;
import com.senontech.service.IAddressService;
import com.senontech.service.IDemandService;
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
@RequestMapping(value = "/address", produces = "application/json;charset=UTF-8")
public class AddressController extends AbstractController{

    @Autowired
    private IAddressService addressService;

    /**
     * 地址信息添加
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Address param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            addressService.add(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "地址信息添加成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "地址信息添加异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 地址删除
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestBody Address param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            addressService.del(param.getAddressId());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "地址信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "地址信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    @ResponseBody
    @RequestMapping(value = "/delList", method = RequestMethod.POST)
    public String delList(@RequestBody Address param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            addressService.delList(param.getAddressIdList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "地址信息删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "地址信息删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 地址信息修改
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Address param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            addressService.edit(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "地址信息修改成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "地址信息修改异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 单个查询地址信息数据
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query( Address param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",addressService.query(param));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "地址信息查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "地址信息查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询地址信息数据列表
     *
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public String queryList() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",addressService.queryList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "地址信息列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "地址信息列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 条件查询列表
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryListByCondition", method = RequestMethod.GET)
    public String queryByLogParam( Address address) {
        Map<String, Object> responseBody = new HashMap();
        responseBody.put(CODE, 1);
        try {
            responseBody.put(DATA, addressService.queryListByCondition(address));
            responseBody.put(CODE, 0);
            responseBody.put("msg", "地址信息分页查询成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "地址信息分页查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }



}
