package com.ssafy.naem.domain.feed.service;

import com.ssafy.naem.domain.board.entity.Board;
import com.ssafy.naem.domain.board.repository.BoardRepository;
import com.ssafy.naem.domain.feed.dto.request.BoardFeedsGetRequest;
import com.ssafy.naem.domain.feed.dto.request.FeedChangeBoardRequest;
import com.ssafy.naem.domain.feed.dto.request.FeedChangeRequest;
import com.ssafy.naem.domain.feed.dto.request.FeedCreateRequest;
import com.ssafy.naem.domain.feed.dto.response.FeedCreateResponse;
import com.ssafy.naem.domain.feed.dto.response.FeedResponse;
import com.ssafy.naem.domain.feed.dto.response.FeedsResponse;
import com.ssafy.naem.domain.feed.entity.Feed;
import com.ssafy.naem.domain.feed.entity.Status;
import com.ssafy.naem.domain.feed.repository.FeedRepository;
import com.ssafy.naem.global.config.BaseException;
import com.ssafy.naem.global.config.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository, BoardRepository boardRepository) {
        this.feedRepository = feedRepository;
        this.boardRepository = boardRepository;
    }

    public FeedResponse getFeed(Long id) {

        Feed foundFeed = feedRepository.findByIdAndStatus(id, Status.STATUS_ACTIVE)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FEED_NOT_FOUND));


        return FeedResponse.from(foundFeed);
    }

    public FeedsResponse getAllFeedsByBoardId(BoardFeedsGetRequest boardFeedsGetRequest, Pageable pageable) {

        Page<Feed> feedPageListResult = feedRepository.findAllByBoard_idAndStatusOrderByCreatedDesc(boardFeedsGetRequest.boardId(), Status.STATUS_ACTIVE, pageable);

        return new FeedsResponse(feedPageListResult);
    }

    public FeedsResponse getAllHiddenFeeds(Pageable pageable) {

        Page<Feed> feedListResult = feedRepository.findAllByStatusOrderByCreatedDesc(Status.STATUS_HIDDEN, pageable);

        return new FeedsResponse(feedListResult);
    }

    @Transactional
    public FeedCreateResponse createFeed(FeedCreateRequest feedCreateRequest) {
        Long boardId = feedCreateRequest.boardId();
        Board board = boardRepository.findByIdAndStatus(boardId, com.ssafy.naem.domain.board.entity.Status.STATUS_ACTIVE)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BOARD_NOT_FOUND));

        Feed newFeed = Feed.builder()
                .board(board)
                .title(feedCreateRequest.title())
                .content(feedCreateRequest.content())
                .build();

        Feed result = feedRepository.save(newFeed);

        return new FeedCreateResponse(result.getId());
    }

    @Transactional
    public void changeFeed(Long id, FeedChangeRequest feedChangeRequest) {
        Feed foundFeed = feedRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FEED_NOT_FOUND));

        String newTitle = feedChangeRequest.title();
        String newContent = feedChangeRequest.content();

        foundFeed.changeContents(newTitle, newContent);
    }

    @Transactional
    public void changeFeedBoard(Long id, FeedChangeBoardRequest feedChangeBoardRequest) {

        Long toBoardId = feedChangeBoardRequest.toBoardId();

        Board toBoard = boardRepository.findByIdAndStatus(toBoardId, com.ssafy.naem.domain.board.entity.Status.STATUS_ACTIVE)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BOARD_NOT_FOUND));

        Feed foundFeed = feedRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FEED_NOT_FOUND));

        if (foundFeed.getBoard().getId() == toBoardId) return;

        foundFeed.changeBoard(toBoard);
    }

    @Transactional
    public void changeFeedVisibility(Long id) {

        Feed foundFeed = feedRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FEED_NOT_FOUND));

        foundFeed.updateStatusToHiddenOrActive();
    }

    @Transactional
    public void deleteFeed(Long id) {

        Feed foundFeed = feedRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FEED_NOT_FOUND));

        foundFeed.updateStatusToDeleted();
    }
}
