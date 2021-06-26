package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    public UserResponse hello() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "aaa")
                .queryParam("age", 99)
                .encode()   //파라미터가 붙으면 인코딩해줘야함
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);   //getForObject http get 메소드
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//        System.out.println(result.getStatusCode());
//        System.out.println(result.getBody());

        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);   //getForEntity : http get 메소드로 요청 보내면 Entity형태로 서버에서 가져오겠다.

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }


}