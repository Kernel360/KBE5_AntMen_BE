package com.antmen.antwork.entity;

import com.antmen.antwork.test.TestResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    public TestResponseDto toDto() {
        return TestResponseDto.builder()
                .id(this.id)
                .content(this.content)
                .build();
    }
}
