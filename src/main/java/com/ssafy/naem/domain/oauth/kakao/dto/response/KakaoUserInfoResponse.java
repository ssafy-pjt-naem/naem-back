package com.ssafy.naem.domain.oauth.kakao.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
public class KakaoUserInfoResponse {

    private String id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    public static class KakaoAccount {
        private String email;
        private Profile profile;

        @Getter
        public static class Profile {

            private String nickname;

            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;

        }

    }

}
