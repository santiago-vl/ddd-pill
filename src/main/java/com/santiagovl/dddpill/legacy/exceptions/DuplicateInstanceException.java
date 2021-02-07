package com.santiagovl.dddpill.legacy.exceptions;

public class DuplicateInstanceException extends RuntimeException {

  public DuplicateInstanceException(final String message) {
    super(message);
  }
}
