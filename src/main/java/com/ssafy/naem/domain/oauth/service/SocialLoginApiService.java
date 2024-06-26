package com.ssafy.naem.domain.oauth.service;

import com.ssafy.naem.domain.oauth.dto.response.OAuthAttributeResponse;

public interface SocialLoginApiService {

    OAuthAttributeResponse getUserInfo(String accessToken);

}