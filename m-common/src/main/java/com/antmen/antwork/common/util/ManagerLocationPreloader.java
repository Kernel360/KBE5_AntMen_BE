package com.antmen.antwork.common.util;

import com.antmen.antwork.common.domain.entity.account.ManagerDetail;
import com.antmen.antwork.common.infra.repository.account.ManagerDetailRepository;
import com.antmen.antwork.common.service.RedisService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagerLocationPreloader {

    private final ManagerDetailRepository managerDetailRepository;
    private final RedisService redisService;

    @PostConstruct
    public void preloadAllManagerLocations() {
        List<ManagerDetail> allDetails = managerDetailRepository.findAll();

        allDetails.stream()
                .filter(detail -> detail.getManagerLatitude() != null && detail.getManagerLongitude() != null)
                .forEach(detail -> {
                    redisService.setManagerLocation(
                            detail.getUserId(),
                            detail.getManagerLatitude(),
                            detail.getManagerLongitude()
                    );
                });

        System.out.println("[Redis 초기화 완료] 매니저 수: " + allDetails.size());
    }
}