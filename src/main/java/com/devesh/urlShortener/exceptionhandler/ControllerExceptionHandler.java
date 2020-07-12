package com.devesh.urlShortener.exceptionhandler;

import com.devesh.urlShortener.exceptions.DuplicateException;
import com.devesh.urlShortener.exceptions.SaveException;
import com.devesh.urlShortener.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SaveException.class)
    public ResponseEntity<Response> handleException(SaveException saveException) {
        Response response = Response.builder()
                .message(saveException.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Response> handleException(DuplicateException duplicateException) {
        Response response = Response.builder()
                .message(duplicateException.getMessage())
                .code(HttpStatus.CONFLICT.value()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
