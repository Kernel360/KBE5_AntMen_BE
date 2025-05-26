package com.antmen.antwork.common.service;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.api.response.BoardListResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antmen.antwork.common.api.request.CreateBoardRequestDto;
import com.antmen.antwork.common.api.request.UpdateBoardRequestDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import com.antmen.antwork.common.exception.BoardNotFoundException;
import com.antmen.antwork.common.exception.UnauthorizedException;
import com.antmen.antwork.common.infra.repository.BoardRepository;
import com.antmen.antwork.common.service.mapper.BoardMapper;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public void boardWrite(String boardType, BoardRequestDto boardRequestDto) {
        boardRepository.save(boardMapper.toEntity(boardRequestDto, boardType));
    @Transactional
    public BoardResponseDto createBoard(CreateBoardRequestDto request) {
        try {
            Board board = Board.builder()
                    .boardType(request.getBoardType())
                    .boardTitle(request.getBoardTitle())
                    .boardContent(request.getBoardContent())
                    .isPinned(false)
                    .isDeleted(false)
                    .build();

            boardRepository.save(board);

            return BoardResponseDto.builder()
                    .msg("게시글 생성에 성공했습니다.")
                    .build();
        } catch (Exception e) {
            log.error("게시글 생성에 실패했습니다.", e);
            return BoardResponseDto.builder()
                    .msg("게시글 생성에 실패했습니다: " + e.getMessage())
                    .build();
        }
    }

    public List<BoardResponseDto> boardReadList(String boardType) {
        return boardRepository.findAll().stream().map(boardMapper::toResponseDto).toList();
    }
    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long boardId) {
        try {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new BoardNotFoundException(boardId));

            if (board.getIsDeleted()) {
                throw new BoardNotFoundException(boardId);
            }

    public ResponseEntity<BoardResponseDto> boardRead(String boardType) {
            return BoardResponseDto.builder().msg("게시글 조회에 성공했습니다.").build();
        } catch (BoardNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("게시글 조회에 실패했습니다.", e);
        }
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, UpdateBoardRequestDto request) {
        try {


            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new BoardNotFoundException(boardId));

            // if (!board.getUserId().equals(userId)) {
            //     throw new UnauthorizedException("게시글 수정 권한이 없습니다.");
            // }

            board.setBoardType(request.getBoardType());
            board.setBoardTitle(request.getBoardTitle());
            board.setBoardContent(request.getBoardContent());
            board.setModifiedAt(LocalTime.now());

            return BoardResponseDto.builder()
                .msg("게시글 수정에 성공했습니다.")
                .build();
        } catch (BoardNotFoundException | UnauthorizedException e) {
            log.warn("게시글 수정 권한 또는 게시글 없음: {}", e.getMessage());
            return BoardResponseDto.builder()
                .msg(e.getMessage())
                .build();
        } catch (Exception e) {
            log.error("게시글 수정에 실패했습니다.", e);
            return BoardResponseDto.builder()
                .msg("게시글 수정에 실패했습니다: " + e.getMessage())
                .build();
        }
    }

    public ResponseEntity<BoardResponseDto> boardUpdate(String boardType, BoardRequestDto boardRequestDto) {
    @Transactional
    public BoardResponseDto deleteBoard(Long boardId) {
        try {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new BoardNotFoundException(boardId));

    }
            // if (!board.getUserId().equals(userId)) {
            //     throw new UnauthorizedException("게시글 삭제 권한이 없습니다.");
            // }

    @DeleteMapping("/{id}")
    public ResponseEntity boardDelete(@PathVariable String boardType) {
            board.setIsDeleted(true);
            board.setModifiedAt(LocalTime.now());

            return BoardResponseDto.builder()
                .msg("게시글 삭제에 성공했습니다.")
                .build();
        } catch (BoardNotFoundException | UnauthorizedException e) {
            log.warn("게시글 삭제 권한 또는 게시글 없음: ", e.getMessage());
            return BoardResponseDto.builder()
                .msg(e.getMessage())
                .build();
        } catch (Exception e) {
            log.error("게시글 삭제에 실패했습니다.", e);
            return BoardResponseDto.builder()
                .msg("게시글 삭제에 실패했습니다: " + e.getMessage())
                .build();
        }
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {
        try {
            return boardRepository.findAll().stream()
                .filter(board -> !board.getIsDeleted())
                .map(board -> BoardResponseDto.builder()
                    .boardId(board.getBoardId())
                    .boardType(board.getBoardType())
                    .boardTitle(board.getBoardTitle())
                    .boardContent(board.getBoardContent())
                    .createdAt(board.getCreatedAt())
                    .modifiedAt(board.getModifiedAt())
                    .isPinned(board.getIsPinned())
                    .build())
                .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("게시글 목록 조회에 실패했습니다.", e);
            // 실패 시에는 빈 리스트 반환 또는 예외 throw
            return List.of();
        }
    }
}
