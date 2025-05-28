package com.antmen.antwork.common.service.mapper;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.api.response.BoardListResponseDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.api.response.CommentResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import com.antmen.antwork.common.domain.entity.Comment;
import com.antmen.antwork.common.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardMapper {

    public Board toEntity(BoardRequestDto boardRequestDto, String boardType, User user);

    public BoardListResponseDto toListResponseDto(Board board);

    public BoardResponseDto toResponseDto(Board board);


}