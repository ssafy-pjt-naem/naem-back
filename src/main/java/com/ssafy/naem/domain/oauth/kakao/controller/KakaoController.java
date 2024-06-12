package com.ssafy.naem.domain.oauth.kakao.controller;

import com.ssafy.naem.domain.oauth.kakao.client.KakaoTokenClient;
import com.ssafy.naem.domain.oauth.kakao.dto.KakaoTokenResponse;
import com.ssafy.naem.domain.oauth.kakao.dto.request.KakaoTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoTokenClient kakaoTokenClient;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @GetMapping("/kakao/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping(path = "/oauth/kakao/callback", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<KakaoTokenResponse> loginCallback(@RequestParam("code") String code) {
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoTokenRequest kakaoTokenRequest = KakaoTokenRequest.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .grant_type("authorization_code")
                .code(code)
                .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                .build();
        KakaoTokenResponse kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequest);
        return ResponseEntity.status(HttpStatus.OK).body(kakaoToken);
    }

}