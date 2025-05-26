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
public class CommentResponseDto {
    private Long commentId;
    private String userName;
    private String commentContent;
    private LocalTime createdAt;
    private LocalTime modifiedAt;
    private List<CommentResponseDto> subComments;
} 