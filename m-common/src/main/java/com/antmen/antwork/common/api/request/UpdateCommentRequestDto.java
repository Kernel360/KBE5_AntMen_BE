package com.antmen.antwork.common.api.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCommentRequestDto {
    private final String commentContent;
} 