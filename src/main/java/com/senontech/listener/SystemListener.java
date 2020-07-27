package com.senontech.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.senontech.config.SystemConfig;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class SystemListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            SystemConfig.init(SystemListener.class.getClassLoader().getResourceAsStream("system.properties"));
            //修改JSON默认配置：取消引用
            JSON.DEFAULT_GENERATE_FEATURE = JSON.DEFAULT_GENERATE_FEATURE|SerializerFeature.DisableCircularReferenceDetect.getMask();
        } catch (IOException e) {
            throw new RuntimeException("系统启动失败", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                //关闭数据库连接资源
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
        //关闭线程池
        immolate();

    }

     /* *//**
     * 销毁未正常关闭的线程
     *//*
    private void destroyThreads(){
        final Set<Thread> threads = Thread.getAllStackTraces().keySet();
        for (Thread thread : threads) {
            if(thread.getName().equals("HouseKeeper")){
                synchronized (this) {
                    try {
                        thread.stop();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }*/

    public Integer immolate() {
        int count = 0;
        try {
            final Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
            threadLocalsField.setAccessible(true);
            final Field inheritableThreadLocalsField = Thread.class.getDeclaredField("inheritableThreadLocals");
            inheritableThreadLocalsField.setAccessible(true);
            for (final Thread thread : Thread.getAllStackTraces().keySet()) {
                count += clear(threadLocalsField.get(thread));
                count += clear(inheritableThreadLocalsField.get(thread));
                if (thread != null) {
                    thread.setContextClassLoader(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    private int clear(final Object threadLocalMap) throws Exception {
        if (threadLocalMap == null)
            return 0;
        int count = 0;
        final Field tableField = threadLocalMap.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        final Object table = tableField.get(threadLocalMap);
        for (int i = 0, length = Array.getLength(table); i < length; ++i) {
            final Object entry = Array.get(table, i);
            if (entry != null) {
                final Object threadLocal = ((WeakReference)entry).get();
                if (threadLocal != null) {
                    Array.set(table, i, null);
                    ++count;
                }
            }
        }
        return count;
    }
}
