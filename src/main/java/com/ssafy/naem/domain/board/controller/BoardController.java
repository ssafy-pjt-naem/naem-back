package com.ssafy.naem.domain.board.controller;

import com.ssafy.naem.global.config.BaseException;
import com.ssafy.naem.global.config.BaseResponse;
import com.ssafy.naem.global.config.BaseResponseStatus;
import com.ssafy.naem.domain.board.dto.request.BoardChangeNameRequest;
import com.ssafy.naem.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.naem.domain.board.dto.response.BoardsResponse;
import com.ssafy.naem.domain.board.dto.response.BoardCreateResponse;
import com.ssafy.naem.domain.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<BoardCreateResponse> createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {

        BoardCreateResponse createBoardResponse = boardService.createBoard(boardCreateRequest);

        return new BaseResponse<>(createBoardResponse);
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<BoardsResponse> getAllBoards() {

        BoardsResponse getAllBoardsResponse = boardService.getAllBoards();

        return new BaseResponse<>(getAllBoardsResponse);
    }

    @ResponseBody
    @GetMapping("/active")
    public BaseResponse<BoardsResponse> getAllActiveBoards() {

        BoardsResponse getAllActiveBoardsResponse = boardService.getAllActiveBoards();

        return new BaseResponse<>(getAllActiveBoardsResponse);
    }

    @ResponseBody
    @PatchMapping("/{id}")
    public BaseResponse<Object> changeBoardName(@PathVariable("id") Long id, @RequestBody BoardChangeNameRequest boardChangeNameRequest) {

        boardService.changeBoardName(id, boardChangeNameRequest);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @ResponseBody
    @PatchMapping("/{id}/hide")
    public BaseResponse<Object> hideBoard(@PathVariable("id") Long id) {

        boardService.hideBoard(id);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @ResponseBody
    @PatchMapping("/{id}/delete")
    public BaseResponse<Object> deleteBoard(@PathVariable("id") Long id) {

        boardService.deleteBoard(id);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
