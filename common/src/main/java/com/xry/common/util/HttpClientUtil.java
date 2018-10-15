package com.xry.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.*;

/**
 * @author yuqinggen
 * @create 2018-08-25 18:43
 */
@Slf4j
public class HttpClientUtil {

    /**
     * 无参数的Get请求
     *
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    public static String doGet(String url) throws ClientProtocolException, IOException {

        return doGet(url, new HashMap<>());
    }

    /**
     * 有参数的Get请求，以object的属性为健，属性值为值
     *
     * @param
     * @param object object的属性只支持基本类型，如果基本类型是包装类型，不设置为null的健值对;如果是非包装类型会设置，如int 设置默认值为0
     * @return
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    public static String doGet(String url, Object object) throws ClientProtocolException, IOException {
        return doGet(url, buildMapParam(object));
    }

    /**
     * 有参数的Get请求
     *
     * @param
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    public static String doGet(String url, Map<String, String> paramMap) throws ClientProtocolException, IOException {
        log.info("get请求，请求的url = {}", url);
        log.info("请求参数 paramMap = {}", paramMap);

        HttpGet httpGet;
        CloseableHttpResponse response;
        String httpEntityContent
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpGet = new HttpGet();
            List<NameValuePair> formParams = setHttpParams(paramMap);
            String param = URLEncodedUtils.format(formParams, "UTF-8");
            httpGet.setURI(URI.create(url + "?" + param));
            response = httpClient.execute(httpGet);
            httpEntityContent = getHttpEntityContent(response);
        }
        httpGet.abort();
        return httpEntityContent;
    }

    /**
     * 无参post请求
     *
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doPost(String url) throws ClientProtocolException, IOException {
        return doPost(url, new HashMap<>());
    }

    /**
     * 有参数的Get请求，以object的属性为健，属性值为值
     *
     * @param url
     * @param object object的属性只支持基本类型，如果基本类型是包装类型，不设置为null的健值对;如果是非包装类型会设置，如int 设置默认值为0
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doPost(String url, Object object) throws ClientProtocolException, IOException {
        return doPost(url, buildMapParam(object));
    }

    /**
     * post请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> paramMap) throws ClientProtocolException, IOException {
        log.info("post请求，请求的url = {}", url);
        log.info("post请求，请求参数 paramMap = {}", paramMap);
        HttpPost httpPost;
        CloseableHttpResponse response;
        String httpEntityContent;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpPost = new HttpPost(url);
            List<NameValuePair> formParams = setHttpParams(paramMap);
            UrlEncodedFormEntity param = new UrlEncodedFormEntity(formParams, "UTF-8");
            //通过setEntity()设置参数给post
            httpPost.setEntity(param);
            //利用httpClient的execute()方法发送请求并且获取返回参数
            response = httpClient.execute(httpPost);
            httpEntityContent = getHttpEntityContent(response);
        }
        httpPost.abort();
        return httpEntityContent;
    }

    /**
     * 请求体为json的post
     *
     * @param url
     * @param object 请求参数
     * @return
     * @throws IOException
     */
    public static String doPostWithJson(String url, Object object) throws IOException {
        return doPostWithJson(url, JSON.toJSONString(object));
    }

    /**
     * 请求体为json的post
     *
     * @param url
     * @param json 请求参数json字符串
     * @return
     */
    public static String doPostWithJson(String url, String json) throws IOException {
        log.info("请求的url = {}", url);
        log.info("请求json参数 json = {}", json);
        HttpPost httpPost;
        CloseableHttpResponse response;
        String httpEntityContent;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpPost = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            //利用httpClient的execute()方法发送请求并且获取返回参数
            response = httpClient.execute(httpPost);
            httpEntityContent = getHttpEntityContent(response);
        }
        httpPost.abort();
        return httpEntityContent;
    }


    /**
     * 设置请求参数
     *
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formParams = new ArrayList<>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formParams;
    }

    /**
     * 获得响应HTTP实体内容
     *
     * @param response
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    private static String getHttpEntityContent(CloseableHttpResponse response) throws IOException {

        InputStream is = null;
        BufferedReader br = null;
        try {

            //通过HttpResponse 的getEntity()方法获取返回信息
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                is = entity.getContent();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line = br.readLine();
                StringBuilder sb = new StringBuilder();
                while (line != null) {
                    sb.append(line + "\n");
                    line = br.readLine();
                }
                log.info("http请求返回结果：response = {}", sb.toString());
                return sb.toString();
            }
        } finally {
            IOUtils.close(br, is, response);
        }
        return "";
    }

    /**
     * 构造请求参数
     *
     * @param object object的属性只支持基本类型，如果基本类型是包装类型，不设置为null的健值对;如果是非包装类型会设置，如int 设置默认值为0
     * @return
     */
    private static Map<String, String> buildMapParam(Object object) {
        Map<String, String> paramMap = new HashMap<>();
        if (StringUtils.isEmpty(object)) {
            log.info("http请求，构造的请求参数为空。");
            return paramMap;
        }
        log.info("http请求参数：object = {}", object);
        Class cls = object.getClass();
        //获取声明的属性
        Field[] field = cls.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            try {
                Field f = field[i];
                //设置属性可以访问
                f.setAccessible(true);
                //属性名
                String key = f.getName();
                //属性值
                Object value = f.get(object);
                if (value != null) {
                    paramMap.put(key, value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return paramMap;
    }
}
