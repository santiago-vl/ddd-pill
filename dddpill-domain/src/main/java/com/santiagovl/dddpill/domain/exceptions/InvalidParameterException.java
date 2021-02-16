package com.santiagovl.dddpill.domain.exceptions;

public class InvalidParameterException extends RuntimeException {

  public InvalidParameterException(final String message) {
    super(message);
  }
}
