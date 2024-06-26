package com.ssafy.naem.domain.login.controller;

import com.ssafy.naem.domain.login.dto.request.OauthLoginRequest;
import com.ssafy.naem.domain.login.dto.response.OauthLoginResponse;
import com.ssafy.naem.domain.login.service.OauthLoginService;
import com.ssafy.naem.domain.member.entity.SocialType;
import com.ssafy.naem.domain.oauth.validator.AuthorizationHeaderUtils;
import com.ssafy.naem.domain.oauth.validator.OauthValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthLoginController {

    private final OauthValidator oauthValidator;
    private final OauthLoginService oauthLoginService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OauthLoginResponse> oauthLogin(@RequestBody OauthLoginRequest oauthLoginRequestDto,
                                                         HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
        oauthValidator.validateSocialType(oauthLoginRequestDto.getSocialType());

        String accessToken = authorizationHeader.split(" ")[1];
        OauthLoginResponse jwtTokenResponse = oauthLoginService
                .oauthLogin(accessToken, SocialType.from(oauthLoginRequestDto.getSocialType()));

        return ResponseEntity.status(HttpStatus.OK).body(jwtTokenResponse);
    }
}
