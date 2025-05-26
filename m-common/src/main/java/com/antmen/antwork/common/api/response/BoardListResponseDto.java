package com.antmen.antwork.common.api.response;

import com.antmen.antwork.common.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class BoardListResponseDto {

    private List<BoardBar> boards;

    @Getter
    @Setter
    @ToString
    public class BoardBar {
        private String boardTitle;
        private String userName;
    }
}
