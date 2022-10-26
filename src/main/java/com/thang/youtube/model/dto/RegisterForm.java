package com.thang.youtube.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterForm {

    private String password;

    private String confirmPassword;

    private String email;

    private String phone;

    private String avatar;
}
