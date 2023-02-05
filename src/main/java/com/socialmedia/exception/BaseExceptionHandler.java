package com.socialmedia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        ApiError error = new ApiError(400,"Validation error","/users");
        Map<String,String> validationErrors = new HashMap<>();

        //validationların tutuldugu datalar
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        error.setValidationErrors(validationErrors);
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ApiError handleUsernameAlreadyExistException() {
        ApiError error = new ApiError(400,"Validation error","/users");
        Map<String,String> validationErrors = new HashMap<>();
        validationErrors.put("username", "Bu kullanıcı adı zaten kullanılmakta!");
        error.setValidationErrors(validationErrors);

        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordNotMatchRegexException.class)
    public ApiError handlePasswordNotMatchRegexException() {
        ApiError error = new ApiError(400,"Validation error","/users");
        Map<String,String> validationErrors = new HashMap<>();
        validationErrors.put("password", "Password minimum 8 harf, büyük, küçük harf ve sayı içermelidir!");
        error.setValidationErrors(validationErrors);
        return error;
    }
}
