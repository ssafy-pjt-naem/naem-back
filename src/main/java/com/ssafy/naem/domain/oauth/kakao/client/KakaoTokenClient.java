package com.ssafy.naem.domain.oauth.kakao.client;

import com.ssafy.naem.domain.oauth.kakao.dto.KakaoTokenResponse;
import com.ssafy.naem.domain.oauth.kakao.dto.request.KakaoTokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenClient")
public interface KakaoTokenClient {

    @PostMapping(value = "/oauth/token", consumes = "application/json")
    KakaoTokenResponse requestKakaoToken(@RequestHeader("Content-Type") String contentType,
                                         @SpringQueryMap KakaoTokenRequest request
    );
}
