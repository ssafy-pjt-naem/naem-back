package com.ssafy.naem.domain.feed.controller;

import com.ssafy.naem.domain.feed.dto.request.BoardFeedsGetRequest;
import com.ssafy.naem.domain.feed.dto.request.FeedChangeBoardRequest;
import com.ssafy.naem.domain.feed.dto.request.FeedChangeRequest;
import com.ssafy.naem.domain.feed.dto.request.FeedCreateRequest;
import com.ssafy.naem.domain.feed.dto.response.FeedCreateResponse;
import com.ssafy.naem.domain.feed.dto.response.FeedResponse;
import com.ssafy.naem.domain.feed.dto.response.FeedsResponse;
import com.ssafy.naem.domain.feed.entity.Feed;
import com.ssafy.naem.domain.feed.service.FeedService;
import com.ssafy.naem.global.config.BaseResponse;
import com.ssafy.naem.global.config.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feeds")
@Tag(name = "Feed", description = "게시글 API")
public class FeedController {
    private final FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "Get Feed", description = "게시글의 상세 내용을 불러온다.")
    public BaseResponse<FeedResponse> getFeed(@PathVariable("id") Long id) {

        FeedResponse getFeedResponse = feedService.getFeed(id);

        return new BaseResponse<>(getFeedResponse);
    }

    @ResponseBody
    @GetMapping("")
    @Operation(summary = "Get All Feeds By Board_Id", description = "선택한 게시판의 게시글 목록을 불러온다. (페이징 적용시, page, size를 파라미터로 추가 (default: page=0&size=5))")
    public BaseResponse<FeedsResponse> getAllFeedsByBoard(@RequestBody BoardFeedsGetRequest boardFeedsGetRequest,
                                                          @PageableDefault(page=0, size = 5) Pageable pageable) {

        FeedsResponse getAllFeedsPageByBoardResponse = feedService.getAllFeedsByBoardId(boardFeedsGetRequest, pageable);

        return new BaseResponse<>(getAllFeedsPageByBoardResponse);
    }

    @ResponseBody
    @GetMapping("/hidden")
    @Operation(summary = "Get All Hidden Feeds", description = "숨김 처리한 게시글 목록을 불러온다. (페이징 적용시, page, size를 파라미터로 추가 (default: page=0&size=5))")
    public BaseResponse<FeedsResponse> getAllHiddenFeeds(@PageableDefault(page=0, size = 5) Pageable pageable) {

        FeedsResponse getAllHiddenFeedsResponse = feedService.getAllHiddenFeeds(pageable);

        return new BaseResponse<>(getAllHiddenFeedsResponse);
    }



    @ResponseBody
    @PostMapping("")
    @Operation(summary = "Create Feed", description = "게시판을 생성한다.")
    public BaseResponse<FeedCreateResponse> createFeed(@RequestBody FeedCreateRequest feedCreateRequest) {

        FeedCreateResponse createFeedResponse = feedService.createFeed(feedCreateRequest);

        return new BaseResponse<>(createFeedResponse);
    }

    @ResponseBody
    @PatchMapping("/{id}")
    @Operation(summary = "Change Feed Infos", description = "게시글의 제목과 내용을 바꾼다.")
    public BaseResponse<Object> changeFeedContents(@PathVariable("id") Long id, @RequestBody FeedChangeRequest feedChangeRequest) {

        feedService.changeFeed(id, feedChangeRequest);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @ResponseBody
    @PatchMapping("/{id}/board")
    @Operation(summary = "Change Feed's Board", description = "게시글의 게시판 정보를 바꾼다.")
    public BaseResponse<Object> changeFeedContents(@PathVariable("id") Long id, @RequestBody FeedChangeBoardRequest feedChangeBoardRequest) {

        feedService.changeFeedBoard(id, feedChangeBoardRequest);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // TODO : 사용자가 자신의 게시글을 숨김, 다시 보이기 처리할 수 있게 해야함
    @ResponseBody
    @PatchMapping("/{id}/visible")
    @Operation(summary = "Hide/Activate Feed", description = "활성 게시글은 숨김 처리, 숨김 게시글은 활성 처리한다.")
    public BaseResponse<Object> changeFeedVisibility(@PathVariable("id") Long id) {

        feedService.changeFeedVisibility(id);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @ResponseBody
    @PatchMapping("/{id}/delete")
    @Operation(summary = "Delete Feed", description = "게시글을 삭제 처리한다.")
    public BaseResponse<Object> deleteFeed(@PathVariable("id") Long id) {

        feedService.deleteFeed(id);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
