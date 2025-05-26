package com.antmen.antwork.common.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDto {
    private Long boardId;
    private String boardType;
    private String boardTitle;
    private String boardContent;
    private LocalTime createdAt;
    private LocalTime modifiedAt;
    private Boolean isPinned;
    private String msg; // 단건 응답용
}
