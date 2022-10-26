package com.thang.youtube.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
    private Long id;
    private String token; //jwt
    private String type = "Bearer";
    private String email;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String accessToken, Long id, String email, Collection<? extends GrantedAuthority> roles) {
        this.token = accessToken;
        this.email = email;
        this.roles = roles;
        this.id = id;
    }
}
