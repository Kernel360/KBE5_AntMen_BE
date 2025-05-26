package com.antmen.antwork.common.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListResponseDto {
    private Long boardId;
    private String userName;
    private String boardTitle;
    private LocalTime createdAt;
    private LocalTime modifiedAt;
    private Boolean isPinned;
    private Boolean isDeleted;
}
