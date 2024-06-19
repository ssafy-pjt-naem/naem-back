package com.ssafy.naem.domain.board.dto.response;

import com.ssafy.naem.domain.board.entity.Board;

public record BoardResponse(Long id, String name) {
    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getName()
        );
    }
}
