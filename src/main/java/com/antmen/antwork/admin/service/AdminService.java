package com.antmen.antwork.admin.service;

import com.antmen.antwork.admin.dto.AdminDTO;
import com.antmen.antwork.admin.repository.AdminRepository;
import com.antmen.antwork.entity.NoticeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * 공지사항 목록 조회 (페이징)
     */
    public Page<AdminDTO> getNotice(Pageable pageable) {
        Page<NoticeEntity> notice = adminRepository.findAll(pageable);
        return notice.map(AdminDTO::fromEntity);
    }

    /**
     * 공지사항 제목 조회
     */
    public Page<AdminDTO> searchByTitle(String keyword, Pageable pageable) {
        Page<NoticeEntity> notice = adminRepository.findByTitleContaining(keyword, pageable);
        return notice.map(AdminDTO::fromEntity);
    }
}
