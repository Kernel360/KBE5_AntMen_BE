package com.antmen.antwork.common.api.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCommentRequestDto {
    private final String commentContent;
    private final Long parentCommentId; // 대댓글용
} 