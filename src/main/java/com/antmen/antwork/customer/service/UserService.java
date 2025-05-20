package com.antmen.antwork.customer.service;

import com.antmen.antwork.customer.dto.UserDto;
import com.antmen.antwork.customer.dto.UserUpdateDto;
import com.antmen.antwork.customer.repository.UserRepository;
import com.antmen.antwork.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserDto userDto) {
        Optional<User> user = userRepository.findByLoginId(userDto.getLoginId());

        if (user.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        userRepository.save(userDto.toEntity());

    }

    public UserDto getUserInfo(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return UserDto.fromEntity(user);

    }

    public void updateUser(Long id, UserUpdateDto userUpdateDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.updateUser(userUpdateDto);

        userRepository.save(user);

    }

    public void deleteUser(Long id) {

        User user = userRepository.findById((id))
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.deleteUser();

        userRepository.save(user);

    }
}
