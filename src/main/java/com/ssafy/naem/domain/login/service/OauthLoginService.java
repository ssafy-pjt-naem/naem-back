package com.ssafy.naem.domain.login.service;

import com.ssafy.naem.domain.login.dto.response.OauthLoginResponse;
import com.ssafy.naem.domain.login.respository.MemberRepository;
import com.ssafy.naem.domain.login.respository.SocialMemberRepository;
import com.ssafy.naem.domain.member.entity.Member;
import com.ssafy.naem.domain.member.entity.SocialType;
import com.ssafy.naem.domain.oauth.dto.response.OAuthAttributeResponse;
import com.ssafy.naem.domain.oauth.entity.SocialMember;
import com.ssafy.naem.domain.oauth.service.SocialLoginApiService;
import com.ssafy.naem.domain.oauth.service.SocialLoginApiServiceFactory;
import com.ssafy.naem.global.jwt.JwtTokenProvider;
import com.ssafy.naem.global.jwt.dto.JwtTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final SocialMemberRepository socialMemberRepository;

    public OauthLoginResponse oauthLogin(String accessToken, SocialType socialType) {
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(socialType);
        OAuthAttributeResponse userInfo = socialLoginApiService.getUserInfo(accessToken);
        log.info("userInfo : {}",  userInfo);

        JwtTokenDto jwtTokenDto;
        Optional<Member> optionalMember = memberRepository.findByEmail(userInfo.getEmail());
        if(optionalMember.isEmpty()) { // 신규 회원 가입
            Member oauthMember = userInfo.toMemberEntity();
            oauthMember = memberRepository.save(oauthMember);
            socialMemberRepository.save(SocialMember.builder()
                    .member(oauthMember)
                    .socialType(socialType)
                    .providerId(userInfo.getProviderId())
                    .build());

            // 토큰 생성
            jwtTokenDto = jwtTokenProvider.createJwtTokenResponse(oauthMember);
            oauthMember.updateRefreshToken(jwtTokenDto.getRefreshToken());
        } else { // 기존 회원일 경우
            Member oauthMember = optionalMember.get();

            // 해당 소셜 맴버 타입이 존재하지 않을 경우
            Optional<SocialMember> optionalSocialMember = socialMemberRepository.findByMemberAndSocialType(oauthMember, socialType);
            if(optionalSocialMember.isEmpty()){
                socialMemberRepository.save(SocialMember.builder()
                        .member(oauthMember)
                        .socialType(socialType)
                        .build());
            }

            // 토큰 생성
            jwtTokenDto = jwtTokenProvider.createJwtTokenResponse(oauthMember);
            oauthMember.updateRefreshToken(jwtTokenDto.getRefreshToken());
        }

        return OauthLoginResponse.of(jwtTokenDto);
    }

}

