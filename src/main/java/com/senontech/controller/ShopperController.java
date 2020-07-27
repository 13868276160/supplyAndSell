package com.senontech.controller;

import com.alibaba.fastjson.JSON;
import com.senontech.entity.Shopper;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.param.PageSize;
import com.senontech.service.IShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/shopper", produces = "application/json;charset=UTF-8")
public class ShopperController extends AbstractController{

    @Autowired
    private IShopperService shopperService;


    /**
     * 买主添加
     * {"account":"pan","name":"pan","password":"123456"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Shopper param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            shopperService.add(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("Msg", "买主添加成功。");
        }catch (ErrorCodeException e){
            e.printStackTrace();
            responseBody.put("Msg", "买主账号已存在,请修改账号。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("Msg", "买主添加异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 买主删除
     * {"userId":"1"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestBody Shopper param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            shopperService.del(param.getShopperId());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("Msg", "买主删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("Msg", "买主删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    @ResponseBody
    @RequestMapping(value = "/delList", method = RequestMethod.POST)
    public String delList(@RequestBody Shopper param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            shopperService.delList(param.getShopperIdList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "买主删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "买主删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 买主修改
     * {"userId":"1","name":"pan1","password":"1234567"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Shopper param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
           shopperService.edit(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("Msg", "买主修改成功。");
        }catch (ErrorCodeException e) {
            e.printStackTrace();
            responseBody.put("Msg", "用户名或密码不正确。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("Msg", "买主修改异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 单个查询买主数据
     * {"userId":"1"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(@RequestBody Shopper param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",shopperService.query(param));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("Msg", "买主查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("Msg", "买主查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询买主数据列表
     * {}
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public String queryList() {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",shopperService.queryList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("Msg", "买主列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("Msg", "买主列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 买主登录接口
     * {"account":"pan1","password":"1234567"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(@RequestBody Shopper param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            Shopper shopper = this.shopperService.shopperLogin(param);
            if(shopper!=null){
                responseBody.put(SUCCESSFUL, 1);
                responseBody.put("Msg", "买主登录成功。");
                responseBody.put("data",shopper);
            }else {
                responseBody.put(SUCCESSFUL, 2);
                responseBody.put("Msg", "买主不存在或者密码错误。");
            }
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("Msg", "买主登录异常。");
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
            responseBody.put(DATA, shopperService.queryPageList(pageSize));
            responseBody.put(CODE, 0);
            responseBody.put("msg", "买主信息分页查询成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "买主信息分页查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

}
