package com.application.core.errorhandling;

import java.time.LocalDateTime;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ProductServiceErrorHandler {

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getLocalizedMessage(), LocalDateTime.now()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleOtherExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getLocalizedMessage(), LocalDateTime.now()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<ErrorMessage> handleCommandExecutionException(CommandExecutionException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getLocalizedMessage(), LocalDateTime.now()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
