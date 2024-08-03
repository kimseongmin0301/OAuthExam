package com.pose.oauth.entity;

import com.pose.oauth.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER")
@Table(name = "USERS")
public class User {
    @Id
    private String userId;
    private String password;
    private String email;
    private String type;
    private String role;

    public User(SignUpRequestDto dto) {
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.type = "app";
        this.role = "ROLE_USER";
    }
}
