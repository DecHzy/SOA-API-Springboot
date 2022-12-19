package org.csu.soa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.csu.soa.service.news.NewsDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
//
//        List<JSONObject> list = new ArrayList<>();
//        for (int i = 0; i < newsArray.size(); i++) {
//            JSONObject newsObject = newsArray.getJSONObject(i);
//            list.add(newsObject);
//        }

        return newsArray.toString();
    }
}
