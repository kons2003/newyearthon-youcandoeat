package com.newyearthon.youcandoeat.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@RestController
//@RequestMapping("/api")
//@RequestMapping("/test")
public class NutritionController {
    /*@GetMapping("/allow_info/basic")
    public String allowBasic() {
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api"); *//*URL*//*
            urlBuilder.append("?" + URLEncoder.encode("keyId", "UTF-8") + "=${openApi.callBackUrl}"); *//*Service Key*//*
            urlBuilder.append("&" + URLEncoder.encode("serviceId", "UTF-8") + "=" + URLEncoder.encode("식품영양성분DB(NEW)", "UTF-8")); *//*페이지 번호*//*
            urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); *//*한 페이지 결과수*//*
            urlBuilder.append("&" + URLEncoder.encode("startIdx", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); *//*한 페이지 결과수*//*
            urlBuilder.append("&" + URLEncoder.encode("endIdx", "UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); *//*한 페이지 결과수*//*
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
    }*/


    NutritionController(){

    }

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
    @Value("${openApi.DESC_KOR}")
    private String descKor;

    @GetMapping("/test/nutrition")
    public String nutrition() throws IOException {

        StringBuilder urlBuilder = new StringBuilder(callBackUrl);
        urlBuilder.append("?" + URLEncoder.encode("keyId","UTF-8") + "="+keyId);
        urlBuilder.append("&" + URLEncoder.encode("serviceId","UTF-8") + "=" + URLEncoder.encode(serviceId, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("startIdx","UTF-8") + "=" + URLEncoder.encode(startIdx, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("endIdx","UTF-8") + "=" + URLEncoder.encode(endIdx, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("DESC_KOR","UTF-8") + "=" + URLEncoder.encode(descKor, "UTF-8"));


         // GET방식으로 전송해서 파라미터 받아오기

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result= sb.toString();
        System.out.println(result);

        return result;
    }


    /*@Value("${openApi.callBackUrl}")
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

    @GetMapping("/apitest")
    public String callApi() {
        StringBuilder result = new StringBuilder();
        try {
            String apiUrl = callBackUrl + "?" +
                    "keyId=" + keyId +
                    "&serviceId=" + serviceId +
                    "&dataType=" + dataType +
                    "&startIdx=" + startIdx +
                    "&endIdx=" + endIdx;
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while ((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }*/

    /*@Value("${openApi.callBackUrl}")
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

    @GetMapping("/nutrition")
    public ResponseEntity<String> callNutritionApi(
           // @RequestParam(value = "DESC_KOR") String descKor
    ) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = callBackUrl + "?" +
                "keyId=" + keyId +
                "&serviceId=" + serviceId +
                "&dataType=" + dataType +
                "&startIdx=" + startIdx +
                "&endIdx=" + endIdx;
                //"&DESC_KOR=" + descKor;

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);

            if (stream != null) stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
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
    }*/
}
