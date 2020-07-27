package com.senontech.listener;

import com.senontech.entity.wechat.AccessToken;
import com.senontech.util.WeiXinParamesUtil;
import com.senontech.util.WeiXinUtil;

import java.util.Date;

/**
 * Created by panjianqiang on 2019/1/23.
 */
public class WeiXinContext {
    private String accessToken;
    private Date expiresDate;//过期时间
    private static WeiXinContext instance;
    private WeiXinContext() {
        accessToken = "accessToken";
        expiresDate = new Date();
    }


    public static WeiXinContext getInstance() {
        if (instance == null) {
            instance = new WeiXinContext();
        }
        return instance;
    }

    public String getAccessToken() {
        //判断是否过期
        if((new Date()).getTime()>=expiresDate.getTime()||accessToken.equals("accessToken")){
           updateAccessToken();
        }
        return accessToken;
    }

    private  synchronized void  updateAccessToken() {
        AccessToken accessToken = WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId,WeiXinParamesUtil.agentSecret);
        this.accessToken = accessToken.getToken();
        this.expiresDate = new Date(accessToken.getExpiresIn()*1000+(new Date()).getTime()-20*1000);
    }

}
