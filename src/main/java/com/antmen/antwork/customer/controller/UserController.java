package com.antmen.antwork.customer.controller;

import com.antmen.antwork.customer.dto.UserDto;
import com.antmen.antwork.customer.dto.UserUpdateDto;
import com.antmen.antwork.customer.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memUser")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> register(
            @Valid
            @RequestBody
            UserDto userDto
    ) {
        userService.createUser(userDto);

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    // 회원정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserInfo(
            @PathVariable
            Long id
    ) {
        return ResponseEntity.ok()
                .body(userService.getUserInfo(id));
    }

    // 회원정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserInfo(
            @PathVariable("id")
            Long id,
            @RequestBody
            UserUpdateDto userUpdateDto) {

        userService.updateUser(id, userUpdateDto);

        return ResponseEntity.ok("사용자 정보가 수정되었습니다.");
    }

    // 회원탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id")
            Long id
    ) {

        userService.deleteUser(id);

        return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
    }


}
