package com.santiagovl.dddpill.legacy.domain.services;

import static org.apache.logging.log4j.util.Strings.isBlank;

import com.santiagovl.dddpill.legacy.domain.exceptions.DuplicateInstanceException;
import com.santiagovl.dddpill.legacy.domain.exceptions.InvalidParameterException;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import com.santiagovl.dddpill.legacy.domain.model.user.repositories.UserRepository;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator {

  private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

  private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

  private final UserRepository userRepository;

  public void validate(final User user) {
    validateMandatoryFields(user);
    validateEmail(user.getEmail());
    validatePassword(user.getPassword());
    validateExistence(user.getEmail());
  }

  private void validateMandatoryFields(final User user) {
    if (isBlank(user.getEmail()) || isBlank(user.getPassword())) {
      throw new InvalidParameterException("Email and password are required");
    }
  }

  private void validateEmail(final String email) {
    final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    final boolean isValidEmail = emailPattern.matcher(email).matches();
    if (!isValidEmail) {
      throw new InvalidParameterException("Invalid email");
    }
  }

  private void validatePassword(final String password) {
    final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    final boolean isValidPassword = passwordPattern.matcher(password).matches();
    if (!isValidPassword) {
      throw new InvalidParameterException("Invalid password");
    }
  }

  private void validateExistence(final String email) {
    final Optional<User> duplicatedUser = userRepository.findByEmail(email);
    if (duplicatedUser.isPresent()) {
      throw new DuplicateInstanceException("User already registered");
    }
  }

}
