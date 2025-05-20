package com.antmen.antwork.admin.controller;

import com.antmen.antwork.admin.dto.AdminDTO;
import com.antmen.antwork.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    public final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 공지사항 조회
     */
    @GetMapping("/notice")
    public ResponseEntity<Page<AdminDTO>> getNotice(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String keyword) {

        Page<AdminDTO> notice;

        if (keyword != null && !keyword.trim().isEmpty()) {
            notice = adminService.searchByTitle(keyword, pageable);
        } else {
            notice = adminService.getNotice(pageable);
        }

        return ResponseEntity.ok(notice);
    }
}
