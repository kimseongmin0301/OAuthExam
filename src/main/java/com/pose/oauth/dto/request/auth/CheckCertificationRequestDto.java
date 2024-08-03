package com.pose.oauth.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckCertificationRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String certificationNumber;
}
