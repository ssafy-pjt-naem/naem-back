package com.ssafy.naem.domain.feed.dto.response;

import com.ssafy.naem.domain.feed.entity.Feed;
import org.springframework.data.domain.Page;

import java.util.List;

public record FeedsResponse(
        List<FeedResponse> feeds,
        int currentPage,
        int totalPages,
        long totalItems
) {
    public FeedsResponse(Page<Feed> feedPage) {
        this(
                feedPage.getContent().stream().map(FeedResponse::from).toList(),
                feedPage.getNumber(),
                feedPage.getTotalPages(),
                feedPage.getTotalElements()
        );
    }
}
