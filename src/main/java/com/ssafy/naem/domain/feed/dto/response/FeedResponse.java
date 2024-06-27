package com.ssafy.naem.domain.feed.dto.response;

import com.ssafy.naem.domain.feed.entity.Feed;

import java.time.LocalDateTime;

public record FeedResponse(Long id, String title, String content, LocalDateTime created, LocalDateTime updated, Long views) {
    public static FeedResponse from(Feed feed) {
        return new FeedResponse(
                feed.getId(),
                feed.getTitle(),
                feed.getContent(),
                feed.getCreated(),
                feed.getUpdated(),
                feed.getViews()
        );
    }
}
