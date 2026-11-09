package com.elilta.javamastery.bank;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex){

        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DuplicateAccountNumberException.class)
    public ResponseEntity<APIErrorResponse> handleDuplicateAccountNumberException(DuplicateAccountNumberException ex){
        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        String fieldName = ex.getBindingResult()
                .getFieldError()
                .getField();

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), fieldName + " " + message);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<APIErrorResponse> handleInsufficientFundsException(InsufficientFundsException ex){
        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);

    }
}
