package com.antmen.antwork.common.api.controller;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.api.request.CommentRequestDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    public final BoardService boardService;

    @PostMapping("/{boardType}")
    public ResponseEntity boardWrite(HttpServletRequest request, @PathVariable String boardType, BoardRequestDto boardRequestDto) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        Long boardId = boardService.boardWrite(boardType, boardRequestDto, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(boardId);
    }

    @GetMapping("/{boardType}")
    public ResponseEntity<List<BoardResponseDto>> boardReadList(@PathVariable String boardType) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.boardReadList(boardType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> boardRead(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(boardService.boardRead(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> boardUpdate(HttpServletRequest request, BoardRequestDto boardRequestDto, @PathVariable Long id) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        return ResponseEntity.status(HttpStatus.OK).body(boardService.boardRead(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity boardDelete(HttpServletRequest request, @PathVariable Long id) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{boardId}")
    public ResponseEntity commentWrite(HttpServletRequest request, @PathVariable Long boardId, CommentRequestDto commentRequestDto) {
        Long userId = (Long) request.getSession().getAttribute("userId");

    }

    @PutMapping("/{boardId}/{commentId}")
    public ResponseEntity commentUpdate(HttpServletRequest request, @PathVariable Long commentId, CommentRequestDto commentRequestDto) {
        Long userId = (Long) request.getSession().getAttribute("userId");

    }

    @DeleteMapping("/{boardId}/{commentId}")
    public ResponseEntity commentDelete(HttpServletRequest request, @PathVariable Long commentId, CommentRequestDto commentRequestDto) {
        Long userId = (Long) request.getSession().getAttribute("userId");

    }

    @PostMapping("/{boardId}/{commentId}")
    public ResponseEntity subcommentWrite(HttpServletRequest request, @PathVariable Long commentId, CommentRequestDto commentRequestDto) {
        Long userId = (Long) request.getSession().getAttribute("userId");

    }
}
