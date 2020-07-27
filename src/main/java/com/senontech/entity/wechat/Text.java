package com.senontech.entity.wechat;

/**
 * Created by panjianqiang on 2019/1/23.
 */

/**
 * 文本消息
 *
 * @author shirayner
 */

public class Text extends BaseMessage {
    //文本
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


