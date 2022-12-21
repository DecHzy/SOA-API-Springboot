package org.csu.soa.service.chatgpt;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 需要挂VPN
 */
public class ChatGPT {
    /**
     * 开放的接口
     */
    public static String chat(String str) {
        return chat(str, "", "");
    }

    /**
     * 开放的接口
     */
    public static String chat(String str, String before, String after) {
        String result = call(str);
        return before + result + after;
    }


    public static void main(String[] args) {
        System.out.println(chat("你好世界"));
    }

    private static String call(String str) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        JSONObject json = new JSONObject();
        //选择模型
        json.set("model", "text-davinci-003");
        //添加我们需要输入的内容
        json.set("prompt", str);
        json.set("temperature", 0.9); // 0~1 越接近0生成随机性越低
        json.set("max_tokens", 100); // 返回长度-少用点
        json.set("top_p", 1);
        json.set("frequency_penalty", 0.0);
        json.set("presence_penalty", 0.6);

        HttpResponse response = HttpRequest.post("https://api.openai.com/v1/completions")
                .headerMap(headers, false)
                .bearerAuth("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")
                .body(String.valueOf(json))
                .timeout(5 * 60 * 1000)
                .execute();

        System.out.println();
        System.out.println(response.body());
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(response.body().toString());

        String returnStr = jsonObject.getJSONArray("choices")
                .getJSONObject(0)
                .get("text").toString();
        return returnStr.trim();
        /*
        response.body()长这样
{
    "id": "cmpl-6PMROl0ezfX1xcrkn1vT0kc2gosqs",
    "object": "text_completion",
    "created": 1671502482,
    "model": "text-davinci-003",
    "choices": [
        {
            "text": "\n\nHi there!",
            "index": 0,
            "logprobs": null,
            "finish_reason": "stop"
        }
    ],
    "usage": {
        "prompt_tokens": 2,
        "completion_tokens": 5,
        "total_tokens": 7
    }
}

         */
    }
}
