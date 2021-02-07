package com.santiagovl.dddpill.legacy.rest;

import com.santiagovl.dddpill.legacy.exceptions.DuplicateInstanceException;
import com.santiagovl.dddpill.legacy.exceptions.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidParameterException.class)
  protected ResponseEntity<String> handleInvalidParameter(final InvalidParameterException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(DuplicateInstanceException.class)
  protected ResponseEntity<String> handleDuplicateInstance(final DuplicateInstanceException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }
}