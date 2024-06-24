package com.lezhin.webtoon.webtoonservice.core.exception;

import com.lezhin.webtoon.webtoonservice.core.dto.ArgumentsNotValidResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        log.error("=== message::: {}", exception.getMessage(), exception);
        final List<ArgumentsNotValidResponse> argumentsNotValidResponseList = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ArgumentsNotValidResponse(
                        ((FieldError) error).getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        if (argumentsNotValidResponseList.size() == 1) {
            return new ResponseEntity<>(argumentsNotValidResponseList.getFirst(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(argumentsNotValidResponseList, HttpStatus.BAD_REQUEST);

    }

}
