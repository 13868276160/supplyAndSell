package com.senontech.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    private static CloseableHttpClient client = null;

    private static final int READ_TIMEOUT = 60000;

    private static final int CONNECT_TIMEOUT = 60000;

    static {
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch(NumberFormatException ignore) {
                        }
                    }
                }
                HttpHost target = (HttpHost) context.getAttribute(
                        HttpClientContext.HTTP_TARGET_HOST);
                return 20 * 1000;
            }

        };
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(20, TimeUnit.SECONDS);
        connManager.setMaxTotal(200);
        connManager.setDefaultMaxPerRoute(200);
        //将目标主机的最大连接数增加到50
        HttpHost localhost = new HttpHost("192.168.0.15", 8080);
        connManager.setMaxPerRoute(new HttpRoute(localhost), 50);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(30 * 1000)
                .setSocketTimeout(30 * 1000)
                .setConnectionRequestTimeout(30 * 1000)
                .build();
        client = HttpClients.custom()
                .setKeepAliveStrategy(myStrategy)
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }


    public static String requestShortConnect(String serviceUrl, String reqUri, String data) throws Exception {
        HttpPost post = new HttpPost(serviceUrl + reqUri);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Auth-ApiUserId","YongXingGroup-mdCloud");
        if (StringUtils.isNotEmpty(data)) {
            StringEntity s = new StringEntity(data, "utf-8");
            post.setEntity(s);
        }
        CloseableHttpResponse postResponse = null;
        String result = null;
        try {
            postResponse = client.execute(post);
            result = IOUtils.toString(postResponse.getEntity().getContent(), "UTF-8");
        } finally {
            IOUtils.closeQuietly(postResponse);
        }
        return result;
    }

    public static String requestLongConnect(String serviceUrl, String reqUri, String data) throws Exception {
        HttpPost post = new HttpPost(serviceUrl + reqUri);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Auth-ApiUserId","YongXingGroup-mdCloud");
        if (StringUtils.isNotEmpty(data)) {
            StringEntity s = new StringEntity(data, "utf-8");
            post.setEntity(s);
        }
        CloseableHttpResponse postResponse = null;
        String result = null;
        try {
            postResponse = client.execute(post);
            result = IOUtils.toString(postResponse.getEntity().getContent(), "UTF-8");
        } finally {
            IOUtils.closeQuietly(postResponse);
        }
        return result;
    }

    /**
     * http get request
     */

    public static String requestGetConnect(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);

            connection.setRequestProperty("Accept-Language","zh-cn,zh;q=0.5");
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
}