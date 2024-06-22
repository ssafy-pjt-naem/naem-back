package com.ssafy.naem.domain.board.controller;

import com.ssafy.naem.global.config.BaseException;
import com.ssafy.naem.global.config.BaseResponse;
import com.ssafy.naem.global.config.BaseResponseStatus;
import com.ssafy.naem.domain.board.dto.request.BoardChangeNameRequest;
import com.ssafy.naem.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.naem.domain.board.dto.response.BoardsResponse;
import com.ssafy.naem.domain.board.dto.response.BoardCreateResponse;
import com.ssafy.naem.domain.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@Tag(name = "Board", description = "게시판 API")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ResponseBody
    @PostMapping("")
    @Operation(summary = "Create Board", description = "게시판을 생성한다.")
    public BaseResponse<BoardCreateResponse> createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {

        BoardCreateResponse createBoardResponse = boardService.createBoard(boardCreateRequest);

        return new BaseResponse<>(createBoardResponse);
    }

    @ResponseBody
    @GetMapping("")
    @Operation(summary = "Get All Boards", description = "(삭제되지 않은) 모든 게시판 목록을 불러온다. (for 관리자)")
    public BaseResponse<BoardsResponse> getAllBoards() {

        BoardsResponse getAllBoardsResponse = boardService.getAllBoards();

        return new BaseResponse<>(getAllBoardsResponse);
    }

    @ResponseBody
    @GetMapping("/active")
    @Operation(summary = "Get All Active Boards", description = "(삭제되지 않은) 모든 활성화 게시판 목록을 불러온다. (default)")
    public BaseResponse<BoardsResponse> getAllActiveBoards() {

        BoardsResponse getAllActiveBoardsResponse = boardService.getAllActiveBoards();

        return new BaseResponse<>(getAllActiveBoardsResponse);
    }

    @ResponseBody
    @PatchMapping("/{id}")
    @Operation(summary = "Change Board Name", description = "게시판의 이름을 바꾼다.")
    public BaseResponse<Object> changeBoardName(@PathVariable("id") Long id, @RequestBody BoardChangeNameRequest boardChangeNameRequest) {

        boardService.changeBoardName(id, boardChangeNameRequest);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @ResponseBody
    @PatchMapping("/{id}/hide")
    @Operation(summary = "Hide Board", description = "게시판을 숨김 처리한다.")
    public BaseResponse<Object> hideBoard(@PathVariable("id") Long id) {

        boardService.hideBoard(id);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @ResponseBody
    @PatchMapping("/{id}/delete")
    @Operation(summary = "Delete Board", description = "게시판을 삭제 처리한다.")
    public BaseResponse<Object> deleteBoard(@PathVariable("id") Long id) {

        boardService.deleteBoard(id);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
