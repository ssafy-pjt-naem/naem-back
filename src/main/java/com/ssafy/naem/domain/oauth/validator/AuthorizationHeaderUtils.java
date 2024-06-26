package com.ssafy.naem.domain.oauth.validator;

import com.ssafy.naem.domain.oauth.constant.GrantType;
import org.springframework.util.StringUtils;

public class AuthorizationHeaderUtils {

    public static void validateAuthorization(String authorizationHeader) {

        if (!StringUtils.hasText(authorizationHeader)) {
            throw new RuntimeException("NOT_ACCESS_TOKEN_TYPE");
        }
        String[] authorizations = authorizationHeader.split(" ");
        if (authorizations.length < 2 || (!GrantType.BEARER.getType().equals(authorizations[0]))) {
            throw new RuntimeException("NOT_ACCESS_TOKEN_TYPE");
        }
    }

}
