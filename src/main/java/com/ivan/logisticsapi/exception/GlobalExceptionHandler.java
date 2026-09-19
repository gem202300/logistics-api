package com.ivan.logisticsapi.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchElementException e) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler (value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                :"Validation failed";
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                error);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException e
    ){
      ErrorResponse error = new ErrorResponse(
              HttpStatus.CONFLICT.value(),
              "Resource already exists"
      );
      return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException e) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
