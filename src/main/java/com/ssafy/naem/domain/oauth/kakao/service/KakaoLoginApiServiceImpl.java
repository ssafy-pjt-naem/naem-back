package com.ssafy.naem.domain.oauth.kakao.service;

import com.ssafy.naem.domain.member.entity.SocialType;
import com.ssafy.naem.domain.oauth.constant.GrantType;
import com.ssafy.naem.domain.oauth.dto.response.OAuthAttributeResponse;
import com.ssafy.naem.domain.oauth.kakao.client.KakaoUserInfoClient;
import com.ssafy.naem.domain.oauth.kakao.dto.response.KakaoUserInfoResponse;
import com.ssafy.naem.domain.oauth.service.SocialLoginApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf8";

    @Override
    public OAuthAttributeResponse getUserInfo(String accessToken) {
        KakaoUserInfoResponse kakaoUserInfoResponseDto = kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE,
                GrantType.BEARER.getType() + " " + accessToken);
        KakaoUserInfoResponse.KakaoAccount kakaoAccount = kakaoUserInfoResponseDto.getKakaoAccount();
        String email = kakaoAccount.getEmail();

        return OAuthAttributeResponse.builder()
                .email(!StringUtils.hasText(email) ? kakaoUserInfoResponseDto.getId() : email)
                .name(kakaoAccount.getProfile().getNickname())
                .profile(kakaoAccount.getProfile().getThumbnailImageUrl())
                .socialType(SocialType.KAKAO)
                .providerId(kakaoUserInfoResponseDto.getId())
                .build();
    }

}
