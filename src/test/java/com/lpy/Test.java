package com.lpy;


import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://jsonplaceholder.typicode.com/posts";
        Dto dto = Dto.builder()
                .body("my message body")
                .userName("lpy")
                .userId("654321")
                .title("my title is a title")
                .build();
        Data str = restTemplate.postForObject(url, dto, Data.class);
        System.out.println(str);

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://jsonplaceholder.typicode.com/posts"))
//                .POST(HttpRequest.BodyPublishers.ofString(dto.toString()))
//                .header("Content-Type", "application/json")
//                .build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//        HttpUtils<Dto, Dto> httpUtils = new HttpUtils<>();
//        httpUtils.setUri("http://jsonplaceholder.typicode.com/posts");
//        Dto post = httpUtils.post(dto, Dto.class);
//        System.out.println(post.toString());
    }
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class HttpUtils<T, D> {
    private String uri;

    @SneakyThrows
    public T post(D d, Class<T> tClass) {
        ParserConfig parserConfig = ParserConfig.getGlobalInstance();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application")
                .POST(HttpRequest.BodyPublishers.ofString(d.toString()))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return JSON.parseObject(httpResponse.body(), tClass);
    }
}


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Data {
    @JSONField(name = "user_id")
    private String userId;
    private String id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "Data{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Dto {
    @JSONField(name = "user_id")
    private String userId;
    private String id;
    private String title;
    private String body;
    @JSONField(name = "user_name")
    private String userName;

    public String toString() {
        return JSON.toJSONString(this);
    }
}
