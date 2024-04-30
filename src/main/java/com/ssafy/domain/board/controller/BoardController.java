package com.ssafy.domain.board.controller;

import com.ssafy.domain.board.dto.BoardRequest;
import com.ssafy.domain.board.dto.BoardResponse;
import com.ssafy.domain.board.model.Board;
import com.ssafy.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public BoardResponse createBoard(@RequestBody BoardRequest boardRequest){
        return boardService.createBoard(boardRequest);
    }

}
