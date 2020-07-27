package com.senontech.controller;

import com.alibaba.fastjson.JSON;
import com.senontech.entity.ProductType;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 产品种类Controller
 */
@Controller
@RequestMapping(value = "/productType", produces = "application/json;charset=UTF-8")
public class ProductTypeController extends AbstractController {

    @Autowired
    private IProductTypeService productTypeService;

    /**
     * 产品种类添加
     * @param param  {"productTypeName":"轧钢设备","parentId":"1"}
     * @return {"SUCCESSFUL":1,"msg":""}
     */
    @ResponseBody
    @RequestMapping(value = "/addProductType", method = RequestMethod.POST)
    public String addProductType(@RequestBody ProductType param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            productTypeService.addProductType(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类添加成功。");
        }catch (ErrorCodeException e){
            e.printStackTrace();
            responseBody.put("msg", "产品种类已存在,请修改名称。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类添加异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 产品种类删除
     * @param param {"productTypeId":"1"}
     * @return {"SUCCESSFUL":1,"msg":""}
     */
    @ResponseBody
    @RequestMapping(value = "/delProductType", method = RequestMethod.POST)
    public String delProductType(@RequestBody ProductType param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            productTypeService.delProductType(param.getProductTypeId());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }


    /**
     * 产品种类列表删除
     * @param param {"productTypeIds":["1","2"]}
     * @return {"SUCCESSFUL":1,"msg":""}
     */
    @ResponseBody
    @RequestMapping(value = "/delProductTypeList", method = RequestMethod.POST)
    public String delProductTypeList(@RequestBody ProductType param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            productTypeService.delProductTypeList(param.getProductTypeIds());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }


    /**
     * 产品种类修改
     * @param param  {"productTypeId":"1","productTypeName":"主变站","parentId":"2"}
     * @return {"SUCCESSFUL":1,"msg":""}
     */
    @ResponseBody
    @RequestMapping(value = "/editProductType", method = RequestMethod.POST)
    public String editProductType(@RequestBody ProductType param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            productTypeService.editProductType(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类修改成功。");
        }catch (ErrorCodeException e){
            e.printStackTrace();
            responseBody.put("msg", "产品种类名称已存在,请重新修改。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类修改异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 单个查询产品种类数据
     * @param productTypeId {"productTypeId":"1"}
     * @return {"SUCCESSFUL":1,"msg":"","data":{}}
     */
    @ResponseBody
    @RequestMapping(value = "/queryProductType", method = RequestMethod.GET)
    public String queryProductType(Integer productTypeId) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",productTypeService.queryProductType(productTypeId));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 根据父类id查询产品种类数据
     * @param parentId {"parentId":"1"}
     * @return {"SUCCESSFUL":1,"msg":"","data":{}}
     */
    @ResponseBody
    @RequestMapping(value = "/queryProductTypeByParentId", method = RequestMethod.GET)
    public String queryProductTypeByParentId(Integer parentId) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",productTypeService.queryProductTypeByParentId(parentId));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询产品种类树状数据
     * @param  {"parentId":"1"}
     * @return {"SUCCESSFUL":1,"msg":"","data":{}}
     */
    @ResponseBody
    @RequestMapping(value = "/queryProductTree", method = RequestMethod.GET)
    public String queryProductTree() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",productTypeService.queryProductTree());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询产品种类数据列表
     * @param  {}
     * @return {"SUCCESSFUL":1,"msg":"","data":[{},{}]}
     */
    @ResponseBody
    @RequestMapping(value = "/queryProductTypeList", method = RequestMethod.GET)
    public String queryProductTypeList() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0)
        ;
        try {
            responseBody.put("data",productTypeService.queryProductTypeList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "产品种类列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "产品种类列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }


}