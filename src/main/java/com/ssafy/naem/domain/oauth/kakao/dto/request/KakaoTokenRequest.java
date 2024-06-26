package com.ssafy.naem.domain.oauth.kakao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class KakaoTokenRequest {

    private String grant_type;
    private String client_id;
    private String redirect_uri;
    private String code;
    private String client_secret;
}

