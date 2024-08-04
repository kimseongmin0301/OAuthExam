package com.pose.oauth.dto.response.auth;

import com.pose.oauth.common.ResponseCode;
import com.pose.oauth.common.ResponseMessage;
import com.pose.oauth.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;

    private SignInResponseDto (String token) {
        super();
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<SignInResponseDto> success (String token) {
        SignInResponseDto res = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public static ResponseEntity<ResponseDto> signInFail () {
        ResponseDto res = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

}
