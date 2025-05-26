package com.antmen.antwork.common.api.controller;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.api.response.BoardListResponseDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import com.antmen.antwork.common.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board/{boardType}")
@RequiredArgsConstructor
public class BoardController {

    public final BoardService boardService;

    @PostMapping
    public ResponseEntity boardWrite(@PathVariable String boardType, BoardRequestDto boardRequestDto) {

    }

    @GetMapping
    public ResponseEntity<BoardListResponseDto> boardReadList(@PathVariable String boardType) {
        return
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> boardRead(@PathVariable String boardType, @PathVariable String id) {

    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> boardUpdate(@PathVariable String boardType, BoardRequestDto boardRequestDto, @PathVariable String id) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity boardDelete(@PathVariable String boardType, @PathVariable String id) {

    }
}
