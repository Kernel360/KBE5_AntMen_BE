package com.antmen.antwork.common.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.antmen.antwork.common.api.request.CreateBoardRequestDto;
import com.antmen.antwork.common.api.request.UpdateBoardRequestDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.service.BoardService;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;
    private BoardController(final BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(
        @RequestBody CreateBoardRequestDto request) {   
            BoardResponseDto response = boardService.createBoard(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> updateBoard(
            @PathVariable Long boardId,
            @RequestBody UpdateBoardRequestDto request) {
                BoardResponseDto response = boardService.updateBoard(boardId, request);
                return ResponseEntity.ok(response);
            }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> deleteBoard(
            @PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.deleteBoard(boardId));
    }
}
