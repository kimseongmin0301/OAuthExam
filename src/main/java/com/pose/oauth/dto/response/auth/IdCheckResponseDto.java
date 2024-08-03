package com.pose.oauth.dto.response.auth;

import com.pose.oauth.common.ResponseCode;
import com.pose.oauth.common.ResponseMessage;
import com.pose.oauth.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class IdCheckResponseDto extends ResponseDto {

    private IdCheckResponseDto() {
        super();
    }

    public static ResponseEntity<IdCheckResponseDto> success() {
        IdCheckResponseDto res = new IdCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto res = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
