package com.santiagovl.dddpill.domain.model.user;

import com.santiagovl.dddpill.domain.exceptions.InvalidParameterException;
import java.util.regex.Pattern;

public class Password {

  private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

  private final String password;

  public Password(final String email) {
    validateEmail(email);
    this.password = email;
  }

  private void validateEmail(final String email) {
    final Pattern emailPattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
    final boolean isValidEmail = emailPattern.matcher(email).matches();
    if (!isValidEmail) {
      throw new InvalidParameterException("Invalid password");
    }
  }

  public String getPassword() {
    return password;
  }
}
