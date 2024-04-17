package com.ssafy.domain.feed.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    CATEGORY_ALL("전체"),
    CATEGORY_HOT("인기"),
    CATEGORY_FREE("자유"),
    CATEGORY_GUARD("보호자"),
    CATEGORY_APPLY("취업"),
    CATEGORY_REHAB("재활 치료"),
    CATEGORY_WELFARE("복지 혜택");

    private final String msg;

    @Override
    public String toString(){
        return msg + "게시판";
    }
}
