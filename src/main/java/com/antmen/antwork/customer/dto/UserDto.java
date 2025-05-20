package com.antmen.antwork.customer.dto;

import com.antmen.antwork.entity.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {

    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    private String phoneNumber;

    private String name;

    private LocalDate birthDate;

    private char gender;

    private String address;

    private String email;

    private int pointBalance;

    private int status;

    public static UserDto fromEntity(User user){

        return UserDto.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .address(user.getAddress())
                .email(user.getEmail())
                .pointBalance(user.getPointBalance())
                .status(user.getStatus())
                .build();
    }

    public User toEntity(){
        return User.builder()
                .id(this.id)
                .loginId(this.loginId)
                .password(this.password)
                .phoneNumber(this.phoneNumber)
                .name(this.name)
                .birthDate(this.birthDate)
                .gender(this.gender)
                .address(this.address)
                .email(this.email)
                .pointBalance(this.pointBalance)
                .status(this.status)
                .build();
    }
}
