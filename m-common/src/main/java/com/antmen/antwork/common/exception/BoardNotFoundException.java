package com.antmen.antwork.common.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Long boardId) {
        super("게시글을 찾을 수 없습니다. (ID: " + boardId + ")");
    }
}