package com.pose.oauth.dto.response.auth;

import com.pose.oauth.common.ResponseCode;
import com.pose.oauth.common.ResponseMessage;
import com.pose.oauth.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {

    private SignUpResponseDto() {
        super();
    }

    public static ResponseEntity<SignUpResponseDto> success() {
        SignUpResponseDto res = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto res = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    public static ResponseEntity<ResponseDto> certificationFail() {
        ResponseDto res = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
}
