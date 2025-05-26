package com.antmen.antwork.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antmen.antwork.common.api.request.CreateCommentRequestDto;
import com.antmen.antwork.common.api.request.UpdateCommentRequestDto;
import com.antmen.antwork.common.api.response.CommentResponseDto;
import com.antmen.antwork.common.domain.entity.Comment;
import com.antmen.antwork.common.exception.CommentNotFoundException;
import com.antmen.antwork.common.exception.UnauthorizedException;
import com.antmen.antwork.common.infra.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createComment(CreateCommentRequestDto request) {
        try {
            Comment comment = Comment.builder()
                    .commentContent(request.getCommentContent())
                    .isDeleted(false)
                    .build();

            commentRepository.save(comment);

            return CommentResponseDto.builder()
                    .msg("댓글 생성에 성공했습니다.")
                    .build();
        } catch (Exception e) {
            log.error("댓글 생성에 실패했습니다.", e);
            return CommentResponseDto.builder()
                    .msg("댓글 생성에 실패했습니다: " + e.getMessage())
                    .build();
        }
    }

    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long commentId) {
        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new CommentNotFoundException(commentId));

            if (comment.getIsDeleted()) {
                throw new CommentNotFoundException(commentId);
            }

            return CommentResponseDto.builder().msg("댓글 조회에 성공했습니다.").build();
        } catch (CommentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("댓글 조회에 실패했습니다.", e);
        }
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, UpdateCommentRequestDto request) {
        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new CommentNotFoundException(commentId));

            // if (!comment.getUserId().equals(userId)) {
            //     throw new UnauthorizedException("댓글 수정 권한이 없습니다.");
            // }

            comment.setCommentContent(request.getCommentContent());
            // comment.setModifiedAt(LocalDateTime.now()); // 필요시 추가

            return CommentResponseDto.builder()
                .msg("댓글 수정에 성공했습니다.")
                .build();
        } catch (CommentNotFoundException | UnauthorizedException e) {
            log.warn("댓글 수정 권한 또는 댓글 없음: {}", e.getMessage());
            return CommentResponseDto.builder()
                .msg(e.getMessage())
                .build();
        } catch (Exception e) {
            log.error("댓글 수정에 실패했습니다.", e);
            return CommentResponseDto.builder()
                .msg("댓글 수정에 실패했습니다: " + e.getMessage())
                .build();
        }
    }

    @Transactional
    public CommentResponseDto deleteComment(Long commentId) {
        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new CommentNotFoundException(commentId));

            // if (!comment.getUserId().equals(userId)) {
            //     throw new UnauthorizedException("댓글 삭제 권한이 없습니다.");
            // }

            comment.setIsDeleted(true);
            // comment.setModifiedAt(LocalDateTime.now()); // 필요시 추가

            return CommentResponseDto.builder()
                .msg("댓글 삭제에 성공했습니다.")
                .build();
        } catch (CommentNotFoundException | UnauthorizedException e) {
            log.warn("댓글 삭제 권한 또는 댓글 없음: {}", e.getMessage());
            return CommentResponseDto.builder()
                .msg(e.getMessage())
                .build();
        } catch (Exception e) {
            log.error("댓글 삭제에 실패했습니다.", e);
            return CommentResponseDto.builder()
                .msg("댓글 삭제에 실패했습니다: " + e.getMessage())
                .build();
        }
    }
} 