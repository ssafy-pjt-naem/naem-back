package com.ssafy.naem.domain.oauth.kakao.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KakaoTokenRequest {

    private String grant_type;
    private String client_id;
    private String redirect_uri;
    private String code;
    private String client_secret;

    @Builder
    public KakaoTokenRequest(String grant_type, String client_id, String redirect_uri, String code, String client_secret) {
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.redirect_uri = redirect_uri;
        this.code = code;
        this.client_secret = client_secret;
    }
}

