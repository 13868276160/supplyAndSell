package com.senontech.controller;

import com.alibaba.fastjson.JSON;
import com.senontech.entity.FuzzyQuery;
import com.senontech.entity.Member;
import com.senontech.exceptions.ErrorCodeException;
import com.senontech.service.IMemberService;
import com.senontech.util.WeiXinParamesUtil;
import com.senontech.util.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Controller
@RequestMapping(value = "/member", produces = "application/json;charset=UTF-8")
public class MemberController extends AbstractController{

    @Autowired
    private IMemberService memberService;


    /**
     * 会员添加
     * {"memberName":"温度","member":"℃"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Member param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            memberService.add(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员添加成功。");
        }catch (ErrorCodeException e){
            e.printStackTrace();
            responseBody.put("msg", "会员已存在。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员添加异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    /**
     * 会员删除
     * {"memberId":"1"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestBody Member param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            memberService.del(param.getMemberId());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 会员修改
     * {"memberId":"1","memberName":"湿度","member":"NULL"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Member param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            memberService.edit(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员修改成功。");
        }catch (ErrorCodeException e){
            e.printStackTrace();
            responseBody.put("msg", "会员已存在。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员修改异常。");
        }
        return JSON.toJSONString(responseBody);
    }
    @ResponseBody
    @RequestMapping(value = "/delList", method = RequestMethod.POST)
    public String delList(@RequestBody Member param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            memberService.delList(param.getMemberIdList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "区域删除成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "区域删除异常。");
        }
        return JSON.toJSONString(responseBody);
    }


    /**
     * 单个查询会员数据
     * {"memberId":"1"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(@RequestBody Member param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",memberService.query(param));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 查询会员数据列表
     * {}
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public String queryList() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",memberService.queryList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 根据拼音排序查询会员数据列表
     * {}
     */
    @ResponseBody
    @RequestMapping(value = "/queryListByCPY", method = RequestMethod.POST)
    public String queryListByCPY() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",memberService.queryListByCPY());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 根据区域排序查询会员数据列表
     * {}
     */
    @ResponseBody
    @RequestMapping(value = "/queryMemberListByArea", method = RequestMethod.POST)
    public String queryMemberListByArea() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",memberService.queryMemberListByArea());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 根据区域排序查询会员数据列表
     * {}
     */
    @ResponseBody
    @RequestMapping(value = "/queryMemberListByProduct", method = RequestMethod.POST)
    public String queryMemberListByProduct() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",memberService.queryMemberListByProduct());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    @ResponseBody
    @RequestMapping(value = "/queryAnalysisList", method = RequestMethod.POST)
    public String queryAnalysisList() {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",memberService.queryList());
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }


    /**
     * 用户登录接口
     * {"account":"pan1","password":"1234567"}
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(@RequestBody Member param) {
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            //判断是否为微信账号登录
            if (param.getCode() != null) {
                //根据微信code获取账号
                String wechatId = WeiXinUtil.getWechatId(param.getCode());
                if (wechatId == null) {
                    responseBody.put(SUCCESSFUL, 0);
                    responseBody.put("msg", "微信账号获取失败。");
                    responseBody.put("data",WeiXinParamesUtil.url);
                } else {
                    param.setWechatId(wechatId);
                    Member member = this.memberService.userLogin(param);
                    if (member != null) {
                        responseBody.put(SUCCESSFUL, 1);
                        responseBody.put("msg", "用户登录成功。");
                        responseBody.put("data", member);
                    } else {
                        responseBody.put(SUCCESSFUL, 2);
                        responseBody.put("msg", "微信账号暂未绑定用户或微信账号名称已更改,请绑定。");
                        responseBody.put("data",WeiXinParamesUtil.url);
                    }
                }
            } else {
                Member member = this.memberService.userLogin(param);
                if (member != null) {
                    responseBody.put(SUCCESSFUL, 1);
                    responseBody.put("msg", "用户登录成功。");
                    responseBody.put("data", member);
                } else {
                    responseBody.put(SUCCESSFUL, 0);
                    responseBody.put("msg", "用户不存在或者密码错误。");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "用户登录异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 根据条件模糊查询并排序
     * {}
     */
    @ResponseBody
    @RequestMapping(value = "/queryFuzzyList", method = RequestMethod.POST)
    public String queryFuzzyList(@RequestBody FuzzyQuery param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            responseBody.put("data",memberService.queryFuzzyList(param));
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "会员列表查询成功。");
        }catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "会员列表查询异常。");
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * 用户绑定微信账号
     * {"code":"1","account":"pan1","password":"1234567"}
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/wechatBinding", method = RequestMethod.POST)
    public String WechatBinding(@RequestBody Member param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            if (param.getCode() != null) {//微信相关操作
                //判断用户账号和密码是否正确
                Member member = this.memberService.query(param);
                if(member==null){//账号密码不存存在
                    responseBody.put(SUCCESSFUL, 0);
                    responseBody.put("msg", "用户账号或者密码错误，请重新绑定。");
                    return JSON.toJSONString(responseBody);
                }
                if(member.getWechatId()!=null){
                    responseBody.put(SUCCESSFUL, 0);
                    responseBody.put("msg", "用户账号已绑定微信账号,请先解绑账号。");
                    return JSON.toJSONString(responseBody);
                }
                //根据code查询账号信息
                String wechatId = WeiXinUtil.getWechatId(param.getCode());
                if (wechatId == null) {
                    responseBody.put(SUCCESSFUL, 0);
                    responseBody.put("msg", "微信账号不存在。");
                    responseBody.put("data", WeiXinParamesUtil.url);
                    return JSON.toJSONString(responseBody);
                } else {
                    List<String> queryWecahtList = this.memberService.queryWechatId();
                    if(queryWecahtList.contains(wechatId)){
                        responseBody.put(SUCCESSFUL, 0);
                        responseBody.put("msg", "微信账号已绑定用户账号，请先解绑账号。");
                        return JSON.toJSONString(responseBody);
                    }
                    param.setWechatId(wechatId);
                    memberService.edit(param);
                    responseBody.put("data", member);
                    responseBody.put(SUCCESSFUL, 1);
                    responseBody.put("msg", "用户绑定微信账号成功。");
                    return JSON.toJSONString(responseBody);
                }
            }
            memberService.edit(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "用户修改成功。");
            return JSON.toJSONString(responseBody);
        } catch (Exception e) {
            responseBody.put("msg", "用户修改异常。");
            return JSON.toJSONString(responseBody);
        }
    }

    /**
     * 用户解除微信账号绑定
     * {"userId":"1"}
     *
     * @param param
     */
    @ResponseBody
    @RequestMapping(value = "/wechatUnbind", method = RequestMethod.POST)
    public String wechatUnbind(@RequestBody Member param) {
        Map<String, Object> responseBody = new WeakHashMap<String, Object>();
        responseBody.put(SUCCESSFUL, 0);
        try {
            param.setWechatId(null);
            this.memberService.unbind(param);
            responseBody.put(SUCCESSFUL, 1);
            responseBody.put("msg", "微信账号解绑成功。");
        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("msg", "微信账号解绑失败。");
            responseBody.put("data",WeiXinParamesUtil.url);
        }
        return JSON.toJSONString(responseBody);
    }
}
