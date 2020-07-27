package com.senontech.controller;

/**
 * 抽象Controller
 */
public abstract class AbstractController {


    protected final static String SUCCESSFUL = "successful";
    protected final static String CODE = "code";
    protected final static String MSG = "msg";
    protected final static String DATA = "data";

    protected final static String ADD_SUCCESS = "添加成功";
    protected final static String ADD_FAIL = "添加失败";
    protected final static String EDIT_SUCCESS = "修改成功";
    protected final static String EDIT_FAIL = "修改失败";
    protected final static String DEL_SUCCESS = "删除成功";
    protected final static String DEL_FAIL = "删除失败";
    protected final static String QUERY_SUCCESS = "查询成功";
    protected final static String QUERY_FAIL = "查询失败";
}