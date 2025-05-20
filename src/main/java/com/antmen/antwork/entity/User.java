package com.antmen.antwork.entity;
import com.antmen.antwork.customer.dto.UserUpdateDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="login_id")
    private String loginId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private char gender;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "point_balance")
    private int pointBalance;

    @Column(name = "status")
    private int status; // 탈퇴 여부

//    @Column(name = "black_list")
//    private Integer blackList; //blacklist 객체 생성

    public void updateUser(UserUpdateDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.password = dto.getPassword();
        this.address = dto.getAddress();
        this.birthDate = dto.getBirthDate();
        this.gender = dto.getGender();
    }

    public void deleteUser() {
        this.status = 0;
    }


}
