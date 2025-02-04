package com.pose.oauth.dto.response.auth;

import com.pose.oauth.common.ResponseCode;
import com.pose.oauth.common.ResponseMessage;
import com.pose.oauth.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class EmailCertificationResponseDto extends ResponseDto {

    private EmailCertificationResponseDto(){
        super();
    }

    public static ResponseEntity<EmailCertificationResponseDto> success() {
        EmailCertificationResponseDto res = new EmailCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto res = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    public static ResponseEntity<ResponseDto> mailSendFail() {
        ResponseDto res = new ResponseDto(ResponseCode.MAIL_FAIL, ResponseMessage.MAIL_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

}
