package com.senontech.config;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConfig {

    //配置文件参数key
    public static final String SERVICE_ALLOYSEVEN_KEY = "alloySevenServiceIpPort"; // a7专属服务



    static SystemConfig single = new SystemConfig();
    private Properties properties;

    private SystemConfig(){}

    public static void init(InputStream config) throws IOException {
        single.properties = new Properties();
        single.properties.load(config);
    }

    public static String getParam(String key){
        return single.properties.getProperty(key);
    }



    public static String getA7Key(){
        String ipPort = single.properties.getProperty(SERVICE_ALLOYSEVEN_KEY);
        if(StringUtils.isNotBlank(ipPort)) {
            return "http://"+ipPort+"/alloySevenService";
        }
        throw new IllegalArgumentException();
    }

}
