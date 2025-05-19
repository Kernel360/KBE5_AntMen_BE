package com.antmen.antwork.test;

import com.antmen.antwork.entity.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestRequestDto {
    private String content;

    public Test toEntity() {
        return Test.builder()
                .content(this.content)
                .build();
    }
}
