package com.santiagovl.dddpill.domain.exceptions;

public class DuplicateInstanceException extends RuntimeException {

  public DuplicateInstanceException(final String message) {
    super(message);
  }
}
