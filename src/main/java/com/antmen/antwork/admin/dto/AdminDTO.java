package com.antmen.antwork.admin.dto;

import com.antmen.antwork.entity.NoticeEntity;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
public class AdminDTO {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Notice 엔티티로부터 DTO 생성
     */
    public static AdminDTO fromEntity(NoticeEntity noticeEntity) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(noticeEntity.getId());
        adminDTO.setTitle(noticeEntity.getTitle());
        adminDTO.setContent(noticeEntity.getContent());
        adminDTO.setCreatedAt(noticeEntity.getCreatedAt());
        adminDTO.setUpdatedAt(noticeEntity.getUpdatedAt());
        return adminDTO;
    }

}
