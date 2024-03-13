package com.sparta.hh99_spring_lv5.model.entity;

import com.sparta.hh99_spring_lv5.model.enumtype.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String email, String password, String gender, String phoneNUm, String address, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNum = phoneNUm;
        this.address = address;
        this.role = role;
    }
}