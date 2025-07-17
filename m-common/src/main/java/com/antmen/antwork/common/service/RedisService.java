package com.antmen.antwork.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final long LOCATION_TTL_SECONDS = 86400L; // 24시간 (필요 시 조정 가능)

    // 위경도 저장 (Hash 형태로 저장)
    public void setManagerLocation(Long managerId, double lat, double lng) {
        String key = buildLocationKey(managerId);
        Map<String, String> locationMap = new HashMap<>();
        locationMap.put("lat", String.valueOf(lat));
        locationMap.put("lng", String.valueOf(lng));

        redisTemplate.opsForHash().putAll(key, locationMap);
        redisTemplate.expire(key, Duration.ofSeconds(LOCATION_TTL_SECONDS));
    }

    // 위경도 조회
    public Optional<double[]> getManagerLocation(Long managerId) {
        String key = buildLocationKey(managerId);
        Map<Object, Object> locationMap = redisTemplate.opsForHash().entries(key);

        if (locationMap == null || locationMap.isEmpty()) {
            return Optional.empty();
        }

        try {
            double lat = Double.parseDouble(locationMap.get("lat").toString());
            double lng = Double.parseDouble(locationMap.get("lng").toString());
            return Optional.of(new double[]{lat, lng});
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Redis Key 생성 규칙
    private String buildLocationKey(Long managerId) {
        return "manager:location:" + managerId;
    }
}