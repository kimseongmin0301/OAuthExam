package com.pose.oauth.dto.response.auth;

import com.pose.oauth.common.ResponseCode;
import com.pose.oauth.common.ResponseMessage;
import com.pose.oauth.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CheckCertificationResponseDto extends ResponseDto {

    private CheckCertificationResponseDto() {
        super();
    }

    public static ResponseEntity<CheckCertificationResponseDto> success() {
        CheckCertificationResponseDto res = new CheckCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public static ResponseEntity<ResponseDto> certificationFail () {
        ResponseDto res = new ResponseDto(ResponseCode.DIFFERENT_CODE, ResponseMessage.DIFFERENT_CODE);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
}
