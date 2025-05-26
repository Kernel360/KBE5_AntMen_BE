package com.antmen.antwork.common.service.mapper;

import com.antmen.antwork.common.api.request.BoardRequestDto;
import com.antmen.antwork.common.api.response.BoardResponseDto;
import com.antmen.antwork.common.domain.entity.Board;
import org.mapstruct.Mapper;

@Mapper
public interface BoardMapper {

    public Board toEntity(BoardRequestDto boardRequestDto, String boardType);

    public BoardResponseDto toResponseDto(Board board);
}
