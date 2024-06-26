package com.ssafy.naem.domain.board.dto.response;

import com.ssafy.naem.domain.board.entity.Board;

public record BoardCreateResponse(Long id) {
    public static BoardCreateResponse from(Board board) {
        return new BoardCreateResponse(
                board.getId()
        );
    }
}
