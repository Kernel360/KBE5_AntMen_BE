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
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final UserRepository userRepository;

    public Long boardWrite(String boardType, BoardRequestDto boardRequestDto, Long userId) {
        User user = userRepository.findById(userId).get();
        Board newBoard = boardRepository.save(boardMapper.toEntity(boardRequestDto, boardType, user));
        return newBoard.getBoardId();
    }

    public List<BoardResponseDto> boardReadList(String boardType) {
        return boardRepository.findAllByBoardType(boardType).stream().map(boardMapper::toResponseDto).toList();
    }


    public BoardResponseDto boardRead(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        if (board.getIsDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }

        return boardMapper.toResponseDto(board);
    }

    public ResponseEntity<BoardResponseDto> boardUpdate(Long id, BoardRequestDto boardRequestDto) {
        return ResponseEntity.ok().build();
    }


//    @Transactional
//    public BoardResponseDto updateBoard(Long boardId, UpdateBoardRequestDto request) {
//        try {
//
//
//            Board board = boardRepository.findById(boardId)
//                    .orElseThrow(() -> new BoardNotFoundException(boardId));
//
//            // if (!board.getUserId().equals(userId)) {
//            //     throw new UnauthorizedException("게시글 수정 권한이 없습니다.");
//            // }
//
//            board.setBoardType(request.getBoardType());
//            board.setBoardTitle(request.getBoardTitle());
//            board.setBoardContent(request.getBoardContent());
//            board.setModifiedAt(LocalTime.now());
//
//            return BoardResponseDto.builder()
//                .msg("게시글 수정에 성공했습니다.")
//                .build();
//        } catch (BoardNotFoundException | UnauthorizedException e) {
//            log.warn("게시글 수정 권한 또는 게시글 없음: {}", e.getMessage());
//            return BoardResponseDto.builder()
//                .msg(e.getMessage())
//                .build();
//        } catch (Exception e) {
//            log.error("게시글 수정에 실패했습니다.", e);
//            return BoardResponseDto.builder()
//                .msg("게시글 수정에 실패했습니다: " + e.getMessage())
//                .build();
//        }
//    }


    public void deleteBoard(Long boardId) {

    }
//    @Transactional
//    public BoardResponseDto deleteBoard(Long boardId) {
//        try {
//            Board board = boardRepository.findById(boardId)
//                    .orElseThrow(() -> new BoardNotFoundException(boardId));
//
//    }
//            // if (!board.getUserId().equals(userId)) {
//            //     throw new UnauthorizedException("게시글 삭제 권한이 없습니다.");
//            // }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity boardDelete(@PathVariable String boardType) {
//            board.setIsDeleted(true);
//            board.setModifiedAt(LocalTime.now());
//
//            return BoardResponseDto.builder()
//                .msg("게시글 삭제에 성공했습니다.")
//                .build();
//        } catch (BoardNotFoundException | UnauthorizedException e) {
//            log.warn("게시글 삭제 권한 또는 게시글 없음: ", e.getMessage());
//            return BoardResponseDto.builder()
//                .msg(e.getMessage())
//                .build();
//        } catch (Exception e) {
//            log.error("게시글 삭제에 실패했습니다.", e);
//            return BoardResponseDto.builder()
//                .msg("게시글 삭제에 실패했습니다: " + e.getMessage())
//                .build();
//        }
//    }
//

}
