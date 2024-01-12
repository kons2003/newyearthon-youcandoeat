package com.newyearthon.youcandoeat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class NutritionController {
    @Value("${openApi.callBackUrl}")
    private String callBackUrl; // 요청주소
    @Value("${openApi.keyId")
    private String keyId; // 인증키
    @Value("${openApi.serviceId}")
    private String serviceId; // 서비스명
    @Value("${openApi.dataType}")
    private String dataType; // 요청파일타입
    @Value("${openApi.startIdx}")
    private String startIdx; // 요청시작위치
    @Value("${openApi.endIdx}")
    private String endIdx; // 요청종료위치

    @GetMapping("/nutrition")
    public ResponseEntity<String> callNutritionApi(
            @RequestParam(value = "DESC_KOR") String descKor
    ) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = callBackUrl +
                "keyId=" + keyId +
                "&serviceId=" + serviceId +
                "&dataType=" + dataType +
                "&startIdx=" + startIdx +
                "&endIdx=" + endIdx +
                "&DESC_KOR=" + descKor;

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);

            if (stream != null) stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // URLConnection을 전달 받아 연결정보 설정 후 연결, 연결 후 수신한 InputStream 반환
    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    // InputStream을 전달받아 문자열로 변환 후 반환
    private String readStreamToString(InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String readLine;
        while ((readLine = br.readLine()) != null) {
            result.append(readLine + "\n\r");
        }

        br.close();

        return result.toString();
    }
}