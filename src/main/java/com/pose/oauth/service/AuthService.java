package com.pose.oauth.service;

import com.pose.oauth.common.CertificationNumber;
import com.pose.oauth.dto.request.auth.*;
import com.pose.oauth.dto.response.ResponseDto;
import com.pose.oauth.dto.response.auth.*;
import com.pose.oauth.entity.Certification;
import com.pose.oauth.entity.User;
import com.pose.oauth.provider.EmailProvider;
import com.pose.oauth.provider.JwtProvider;
import com.pose.oauth.repository.CertificationRepository;
import com.pose.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;
    private final CertificationRepository certificationRepository;

    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return IdCheckResponseDto.duplicateId();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccess = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccess) return EmailCertificationResponseDto.mailSendFail();

            Certification certification = new Certification(userId, email, certificationNumber);
            certificationRepository.save(certification);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {

            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            Certification certification = certificationRepository.findByUserId(userId);
            if (certification == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatch = certification.getEmail().equals(email) && certification.getCertificationNumber().equals(certificationNumber);
            if(!isMatch) return CheckCertificationResponseDto.certificationFail();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {

            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return SignUpResponseDto.duplicateId();

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            Certification certification = certificationRepository.findByUserId(userId);
            boolean isMatch = certification.getEmail().equals(email) && certification.getCertificationNumber().equals(certificationNumber);
            if(!isMatch) return SignUpResponseDto.certificationFail();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);

            SignUpRequestDto encodedPasswordDto = dto.toBuilder()
                    .password(encodedPassword)
                    .build();

            User user = new User(encodedPasswordDto);
            userRepository.save(user);

            certificationRepository.deleteById(userId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = "";
        try {
            String userId = dto.getId();
            User user = userRepository.findByUserId(userId);
            if (user == null) SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = user.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.create(userId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}
