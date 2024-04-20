package com.ssafy.domain.member.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberType {
    SOCIAL("소셜로그인"),
    BASIC("기본");

    private final String msg;

    @Override
    public String toString() {
        return msg + " 회원";
    }
}
