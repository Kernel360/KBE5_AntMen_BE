package com.antmen.antwork.common.service;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.api.response.BoardListResponseDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.infra.repository.BoardRepository;
import com.antmen.antwork.common.service.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public void boardWrite(String boardType, BoardRequestDto boardRequestDto) {
        boardRepository.save(boardMapper.toEntity(boardRequestDto, boardType));
    }

    public List<BoardResponseDto> boardReadList(String boardType) {
        return boardRepository.findAll().stream().map(boardMapper::toResponseDto).toList();
    }

    public ResponseEntity<BoardResponseDto> boardRead(String boardType) {

    }

    public ResponseEntity<BoardResponseDto> boardUpdate(String boardType, BoardRequestDto boardRequestDto) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity boardDelete(@PathVariable String boardType) {

    }
}
