package com.att.tdp.popcorn_palace.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IncorrectFieldException.class, ResourceAlreadyExistsException.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleIncorrectFieldException(Exception exception, WebRequest request) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", exception.getMessage());
        HttpStatus httpStatus;
        switch (exception) {
            case IncorrectFieldException e -> httpStatus = HttpStatus.BAD_REQUEST;
            case ResourceAlreadyExistsException e -> httpStatus = HttpStatus.CONFLICT;
            case ResourceNotFoundException e -> httpStatus = HttpStatus.NOT_FOUND;
            default -> httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return handleExceptionInternal(exception, responseMap, new HttpHeaders(), httpStatus, request);
    }
}
