package com.antmen.antwork.common.service;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.domain.entity.User;
import com.antmen.antwork.common.infra.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import com.antmen.antwork.common.infra.repository.BoardRepository;
import com.antmen.antwork.common.service.mapper.BoardMapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final UserRepository userRepository;

    @Transactional
    public BoardResponseDto boardWrite(String boardType, BoardRequestDto boardRequestDto, Long userId) {
        User user = userRepository.findById(userId).get();
        Board newBoard = boardRepository.save(boardMapper.toEntity(boardRequestDto, boardType, user));
        return boardMapper.toResponseDto(newBoard);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> boardReadList(String boardType) {
        return boardRepository.findAllByBoardType(boardType).stream().map(boardMapper::toResponseDto).toList();
    }

    @Transactional(readOnly = true)
    public BoardResponseDto boardRead(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        if (board.getBoardIsDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }

        return boardMapper.toResponseDto(board);
    }

    @Transactional
    public BoardResponseDto boardUpdate(Long userId, Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        if (board.getBoardIsDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제된 게시글 입니다.");
        }

        if (board.getBoardUser().getUserId() != userId){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인이 작성한 글만 수정 가능합니다.");
        }

        board.setBoardTitle(boardRequestDto.getBoardTitle());
        board.setBoardContent(boardRequestDto.getBoardContent());
        board.setIsPinned(boardRequestDto.getBoardIsPinned());

        return boardMapper.toResponseDto(board);
    }

    @Transactional
    public void deleteBoard(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        if (board.getBoardIsDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제된 게시글 입니다.");
        }

        if (board.getBoardUser().getUserId() != userId){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인이 작성한 글만 삭제 가능합니다.");
        }

        board.setBoardIsDeleted(true);
    }

}
