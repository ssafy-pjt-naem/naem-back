package com.ssafy.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponse {

    private String msg;

    @Builder
    public BoardResponse(String msg){
        this.msg = msg;
    }

}
