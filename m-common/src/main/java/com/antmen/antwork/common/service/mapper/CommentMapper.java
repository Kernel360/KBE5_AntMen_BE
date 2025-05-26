package com.antmen.antwork.common.service.mapper;

import com.antmen.antwork.common.api.response.CommentResponseDto;
import com.antmen.antwork.common.domain.entity.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {
    public Comment toEntity();

    public CommentResponseDto toResponseDto(Comment comment);
}
