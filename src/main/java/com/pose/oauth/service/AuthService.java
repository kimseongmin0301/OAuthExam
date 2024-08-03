package com.pose.oauth.service;

import com.pose.oauth.dto.request.auth.IdCheckRequestDto;
import com.pose.oauth.dto.response.ResponseDto;
import com.pose.oauth.dto.response.auth.IdCheckResponseDto;
import com.pose.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

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
}
