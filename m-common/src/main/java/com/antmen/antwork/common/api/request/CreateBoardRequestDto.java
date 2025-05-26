package com.antmen.antwork.common.api.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBoardRequestDto {
    private final String boardType;
    private final String boardTitle;
    private final String boardContent;
}