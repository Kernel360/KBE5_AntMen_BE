package com.antmen.antwork.common.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.antmen.antwork.common.api.request.CreateCommentRequestDto;
import com.antmen.antwork.common.api.request.UpdateCommentRequestDto;
import com.antmen.antwork.common.api.response.CommentResponseDto;
import com.antmen.antwork.common.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CreateCommentRequestDto request) {
        CommentResponseDto response = commentService.createComment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequestDto request) {
        CommentResponseDto response = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
} 