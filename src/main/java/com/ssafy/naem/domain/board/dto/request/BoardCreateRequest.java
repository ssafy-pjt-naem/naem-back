package com.ssafy.naem.domain.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시판 이름은 중복될 수 없음")
public record BoardCreateRequest (String name){
}
