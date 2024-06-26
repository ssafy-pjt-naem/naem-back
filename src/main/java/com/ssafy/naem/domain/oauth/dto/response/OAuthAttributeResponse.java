package com.ssafy.naem.domain.oauth.dto.response;

import com.ssafy.naem.domain.member.entity.Member;
import com.ssafy.naem.domain.member.entity.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter @Builder
public class OAuthAttributeResponse {

    private String name;
    private String email;
    private String profile;
    private SocialType socialType;
    private String providerId;

    public Member toMemberEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .profile(profile)
                .build();
    }

}
