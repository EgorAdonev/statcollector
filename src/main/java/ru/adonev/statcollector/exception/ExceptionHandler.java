/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<StatCollectorError> catchEventException(EventException exception){
        System.err.println(exception.getMessage());
        return new ResponseEntity<>(new StatCollectorError(HttpStatus.BAD_REQUEST.value(), exception.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
