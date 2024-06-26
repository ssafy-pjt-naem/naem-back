package com.ssafy.naem.domain.oauth.validator;

import com.ssafy.naem.domain.member.entity.SocialType;
import org.springframework.stereotype.Service;


@Service
public class OauthValidator {

    public void validateSocialType(String socialType) {
        if(!SocialType.isSocialType(socialType)) {
            throw new RuntimeException("INVALID_MEMBER_TYPE");
        }
    }

}
