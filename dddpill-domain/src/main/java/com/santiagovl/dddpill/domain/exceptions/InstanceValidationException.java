package com.santiagovl.dddpill.domain.exceptions;

public class InstanceValidationException extends RuntimeException {

  public InstanceValidationException(final String message) {
    super(message);
  }
}