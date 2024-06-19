package com.ssafy.naem.domain.board.controller;

import com.ssafy.naem.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.naem.domain.board.entity.Board;
import com.ssafy.naem.domain.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/new")
    public void createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {
        boardService.createBoard(boardCreateRequest);
    }

    @PostMapping("/hide/{id}")
    public void hideBoard(@PathVariable("id") Long id) {
        boardService.hideBoard(id);
    }

    @PostMapping("/delete/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
    }

}
