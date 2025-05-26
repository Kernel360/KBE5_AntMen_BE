package com.antmen.antwork.common.service.mapper;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.api.response.BoardListResponseDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import com.antmen.antwork.common.domain.entity.Comment;
import com.antmen.antwork.common.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface BoardMapper {

    @Mapping(target = "boardType", source = "boardType")
    @Mapping(target = "boardUser", source = "user")
    public Board toEntity(BoardRequestDto boardRequestDto, String boardType, User user);

    @Mapping(target = "userName", expression = "java(board.getUser().getName()")
    public BoardListResponseDto toListResponseDto(Board board);

    @Mapping(target = "userName", expression = "java(board.getUser().getName())")
    public BoardResponseDto toResponseDto(Board board);

}
