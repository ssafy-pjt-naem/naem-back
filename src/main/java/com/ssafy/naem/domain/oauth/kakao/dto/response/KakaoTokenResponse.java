package com.ssafy.naem.domain.oauth.kakao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class KakaoTokenResponse {

        private String token_type;
        private String access_token;
        private Integer expires_in;
        private String refresh_token;
        private Integer refresh_token_expires_in;
        private String scope;

        @Builder
        public KakaoTokenResponse(String token_type, String access_token, Integer expires_in, String refresh_token, Integer refresh_token_expires_in, String scope) {
            this.token_type = token_type;
            this.access_token = access_token;
            this.expires_in = expires_in;
            this.refresh_token = refresh_token;
            this.refresh_token_expires_in = refresh_token_expires_in;
            this.scope = scope;
        }
}
