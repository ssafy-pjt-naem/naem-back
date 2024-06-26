package com.ssafy.naem.domain.board.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    STATUS_DELETED("삭제"),
    STATUS_ACTIVE("활성"),
    STATUS_HIDDEN("숨김");

    private final String msg;

    @Override
    public String toString(){
        return msg + "게시글";
    }
}
