package com.sparta.hh99_spring_lv5.model.dto;

import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @Email
    private String email;

    @Pattern(regexp = "^[A-Za-z\\d@$!%*?&]{8,15}$")
    private String password;

    private String gender;
    private String phoneNum;
    private String address;
    private UserRoleEnum role;
}