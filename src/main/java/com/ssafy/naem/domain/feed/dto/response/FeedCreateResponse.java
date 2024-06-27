package com.ssafy.naem.domain.feed.dto.response;

import com.ssafy.naem.domain.feed.entity.Feed;

public record FeedCreateResponse(Long id) {
    public static FeedCreateResponse from(Feed feed) {
        return new FeedCreateResponse(
                feed.getId()
        );
    }
}
