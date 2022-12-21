package org.csu.soa.controller;


import org.apache.http.HttpResponse;
import org.csu.soa.service.news.HttpUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云购买的短信服务
 */
@RestController
@RequestMapping("/sms")
public class SMSController {

    @PostMapping("/weather")
    public void weatherSMS(@RequestParam(required = true) String phone,
                           @RequestParam(required = true) String max,
                           @RequestParam(required = true) String min) {
        f(phone, max, min);
    }

    public static void main(String[] args) {
        // 示例
        /*
         * 【国阳云】尊敬的会员，今天25℃～18℃，天气变冷，记得加衣保暖哦！
         */
        f("18900381020", "10", "2");
    }

    public static void f(String phone, String max, String min) {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "8f6fdadab3044adbb5f5f2d374dabbbf";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "**max**:" + max + ",**min**:" + min);
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "5d5e424e56674d23b8c69a9c4cc26a50");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
