package com.antmen.antwork.common.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDto {
    private Long boardId;
    private String userName;
    private String boardTitle;
    private String boardContent;
    private LocalTime createdAt;
    private LocalTime modifiedAt;
    private List<CommentResponseDto> comments;
}
