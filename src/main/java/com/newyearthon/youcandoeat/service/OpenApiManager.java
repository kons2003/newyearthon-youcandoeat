/*
package com.newyearthon.youcandoeat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Component
public class OpenApiManager {
    @Value("${openApi.callBackUrl}")
    private String callBackUrl; // 요청주소
    @Value("${openApi.keyId}")
    private String keyId; // 인증키
    @Value("${openApi.serviceId}")
    private String serviceId; // 서비스명
    @Value("${openApi.dataType}")
    private String dataType; // 요청파일타입
    @Value("${openApi.startIdx}")
    private String startIdx; // 요청시작위치
    @Value("${openApi.endIdx}")
    private String endIdx; // 요청종료위치

    private String makeUrl() throws UnsupportedEncodingException {
        return callBackUrl +
                "?" + keyId +
               "&" + serviceId +
                "&" + dataType +
                "&" + startIdx +
                "&" + endIdx;
    }

    public ResponseEntity<?> fetch() throws UnsupportedEncodingException {
        System.out.println(makeUrl());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> resultMap = restTemplate.exchange(makeUrl(), HttpMethod.GET, entity, Map.class);
        System.out.println(resultMap.getBody());
        return resultMap;
    }
}
*/
