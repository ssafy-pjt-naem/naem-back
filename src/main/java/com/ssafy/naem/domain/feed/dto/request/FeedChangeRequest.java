package com.ssafy.naem.domain.feed.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record FeedChangeRequest(String title, String content){
}
