package com.antmen.antwork.common.service.mapper;

import com.antmen.antwork.common.api.request.CommentRequestDto;
import com.antmen.antwork.common.api.response.CommentResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import com.antmen.antwork.common.domain.entity.Comment;
import com.antmen.antwork.common.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


//@Mapper(
//        unmappedTargetPolicy = ReportingPolicy.IGNORE,
//        componentModel = "spring"
//)
public interface CommentMapper {
//    @Mapping(target = "board", source = "board")
//    @Mapping(target = "commentUser", source = "user")
    public Comment toEntity(Board board, User user, CommentRequestDto commentRequestDto);

//    @Mapping(target = "board", source = "board")
//    @Mapping(target = "commentUser", source = "user")
//    @Mapping(target = "parentComment", source = "parentComment")
    public Comment toEntity(Board board, User user, Comment parentComment, CommentRequestDto commentRequestDto);

//    @Mapping(target = "userName", expression = "java(comment.getCommentUser().getUserName())")
    public CommentResponseDto toResponseDto(Comment comment);

}
