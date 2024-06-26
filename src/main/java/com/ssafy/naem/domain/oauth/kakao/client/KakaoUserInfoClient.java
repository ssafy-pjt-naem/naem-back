package com.ssafy.naem.domain.oauth.kakao.client;

import com.ssafy.naem.domain.oauth.kakao.dto.response.KakaoUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoUserInfoClient")
public interface KakaoUserInfoClient {

    @GetMapping(value = "/v2/user/me", consumes = "application/json")
    KakaoUserInfoResponse getKakaoUserInfo(@RequestHeader("Content-type") String contentType,
                                           @RequestHeader("Authorization") String accessToken);

}