package org.csu.soa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.csu.soa.service.news.NewsDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {
    @GetMapping("/")
    public String getNews(@RequestParam(required = false) String keyword) throws Exception {
        JSONArray newsArray;
        if (keyword == null || keyword.equals("")) {
            newsArray = NewsDemo.newsList("");
        } else {
            newsArray = NewsDemo.newsList(keyword);
        }

        JSONArray returnNewsArray = new JSONArray();
        for (int i = 0; i < newsArray.size(); i++) {
            JSONObject jsonObject = newsArray.getJSONObject(i);
            //System.out.println("当前新闻json数据~~~~~~~~~~~~~~~~");
            //System.out.println(jsonObject);
            //System.out.println("结束-当前新闻json数据~~~~~~~~~~~~~~~~");
            if (jsonObject.get("desc") == null) {
                //System.out.println("没有desc标签~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                //System.out.println(jsonObject);
                //System.out.println("结束-没有desc标签~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                returnNewsArray.add(jsonObject);
                continue;
            } else if (jsonObject.get("link") == null) {
                //System.out.println("没有link标签~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                //System.out.println(jsonObject);
                //System.out.println("结束-没有link标签~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                returnNewsArray.add(jsonObject);
                continue;
            } else {
                returnNewsArray.add(jsonObject);
            }

        }
        return returnNewsArray.toString();
    }
}
