package com.antmen.antwork.common.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.antmen.antwork.common.domain.entity.Category;

@Getter
@Setter
@Builder
public class ReservationRequestDto {
    private Long customerId;                    // 수요자 아이디
    private Long categoryId;                    // 카테고리 번호
    private Long addressId;                     // 기본 주소 ID (프론트에서 선택)

    private LocalDate reservationCreatedAt;     // 신청 날짜
    private String reservationDate;             // 예약 날짜
    private Time reservationTime;               // 예약 시간
    private short reservationDuration;          // 서비스 제공 시간
    private String reservationMemo;             // 추가 요청 사항 (엔티티와 이름 일치)
    private Integer reservationAmount;          // 최종 가격 (프론트에서 계산)
    private short additionalDuration;           // 사용자가 추가한 시간
    private List<Long> optionIds;               // 선택한 옵션 리스트
}
