package com.ssafy.naem.global.util;

import com.ssafy.naem.global.config.BaseException;
import com.ssafy.naem.global.config.BaseResponse;
import com.ssafy.naem.global.config.BaseResponseStatus;
import com.ssafy.naem.global.config.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException exception) {
        ErrorResponse response = new ErrorResponse(exception.getStatus());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Unchecked Exception 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse response = new ErrorResponse(BaseResponseStatus.DATABASE_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
