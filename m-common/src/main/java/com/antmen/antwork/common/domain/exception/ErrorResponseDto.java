package com.antmen.antwork.common.domain.exception;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class ErrorResponseDto {
    private String code;
    private String message;
}
