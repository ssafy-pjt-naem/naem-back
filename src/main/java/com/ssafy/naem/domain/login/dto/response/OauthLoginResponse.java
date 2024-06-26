package com.ssafy.naem.domain.login.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.naem.global.jwt.dto.JwtTokenDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OauthLoginResponse {

    private String grantType;

    private String accessToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate accessTokenExpireTime;

    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate refreshTokenExpireTime;

    public static OauthLoginResponse from(JwtTokenDto jwtTokenDto) {
        return OauthLoginResponse.builder()
                .grantType(jwtTokenDto.getGrantType())
                .accessToken(jwtTokenDto.getAccessToken())
                .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                .refreshToken(jwtTokenDto.getRefreshToken())
                .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                .build();
    }

}

