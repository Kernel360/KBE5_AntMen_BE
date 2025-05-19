package com.antmen.antwork.test;

import com.antmen.antwork.entity.Test;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestService {
    public final TestRepository testRepository;

    @Transactional
    public TestResponseDto postContent(TestRequestDto testRequestDto) {
        return testRepository.findById(testRepository.save(testRequestDto.toEntity()).getId())
                .orElseThrow(() -> new EntityNotFoundException("저장된 엔티티를 못찾았습니다.")).toDto();
    }
}
