package com.santiagovl.dddpill.domain.model.user;

import com.santiagovl.dddpill.domain.exceptions.InvalidParameterException;
import java.util.regex.Pattern;

public class Email {

  private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

  private final String email;

  public Email(final String email) {
    validateEmail(email);
    this.email = email;
  }

  private void validateEmail(final String email) {
    final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    final boolean isValidEmail = emailPattern.matcher(email).matches();
    if (!isValidEmail) {
      throw new InvalidParameterException("Invalid email");
    }
  }

  public String getEmail() {
    return email;
  }
}
