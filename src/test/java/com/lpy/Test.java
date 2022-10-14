package com.lpy;

import lombok.*;
import org.springframework.web.client.RestTemplate;


public class Test {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:6854/demo";
        Dto dto = Dto.builder()
                .body("my message body")
                .userName("lpy")
                .userId("654321")
                .title("my title is a title")
                .build();
        Data str = restTemplate.postForObject(url, dto, Data.class);
        System.out.println(str);
    }
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Data {
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
    private String userId;
    private String id;
    private String title;
    private String body;
    private String userName;
}
