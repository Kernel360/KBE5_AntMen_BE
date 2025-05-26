package com.antmen.antwork.common.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.antmen.antwork.common.api.response.CommentResponseDto;
import com.antmen.antwork.common.service.CommentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

    private final CommentService commentService;

    @PostMapping("/board/{boardId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long boardId,
            @RequestBody CreateCommentRequestDto request) {
        CommentResponseDto response = commentService.createComment(boardId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/board/{boardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getComments(boardId));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequestDto request) {
        CommentResponseDto response = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}