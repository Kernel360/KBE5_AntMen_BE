package com.antmen.antwork.common.service.mapper;

import com.antmen.antwork.common.api.request.CommentRequestDto;
import com.antmen.antwork.common.api.response.CommentResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import com.antmen.antwork.common.domain.entity.Comment;
import com.antmen.antwork.common.domain.entity.User;
import com.antmen.antwork.common.infra.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {

    private final CommentRepository commentRepository;

    @Override
    public Comment toEntity(Board board, User user, CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getParentId() == null) {
            return Comment.builder()
                    .commentUser(user)
                    .board(board)
                    .commentContent(commentRequestDto.getContent())
                    .commentIsDeleted(false)
                    .build();
        } else {
            return Comment.builder()
                    .commentUser(user)
                    .board(board)
                    .commentContent(commentRequestDto.getContent())
                    .parentComment(commentRepository.findById(commentRequestDto.getParentId()).orElse(null))
                    .commentIsDeleted(false)
                    .build();
        }
    }

    @Override
    public Comment toEntity(Board board, User user, Comment parentComment, CommentRequestDto commentRequestDto) {
        return Comment.builder()
                .commentUser(user)
                .board(board)
                .commentContent(commentRequestDto.getContent())
                .parentComment(parentComment)
                .commentIsDeleted(false)
                .build();
    }

    @Override
    public CommentResponseDto toResponseDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .userName(comment.getCommentUser() != null ? comment.getCommentUser().getUserName() : null)
                .content(comment.getCommentContent())
                .createdAt(comment.getCommentCreatedAt())
                .modifiedAt(comment.getCommentModifiedAt())
                .isDeleted(comment.getCommentIsDeleted())
                .parentId(comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null)
                .build();
    }
}
