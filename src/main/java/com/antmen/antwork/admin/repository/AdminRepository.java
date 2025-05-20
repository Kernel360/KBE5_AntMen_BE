package com.antmen.antwork.admin.repository;

import com.antmen.antwork.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<NoticeEntity, Integer> {
    /**
     * 공지사항 제목 검색
     */
    Page<NoticeEntity> findByTitleContaining(String title, Pageable pageable);
}
