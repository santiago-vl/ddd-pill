package com.santiagovl.dddpill.domain.services;

import com.santiagovl.dddpill.domain.exceptions.DuplicateInstanceException;
import com.santiagovl.dddpill.domain.model.user.Email;
import com.santiagovl.dddpill.domain.model.user.User;
import com.santiagovl.dddpill.domain.model.user.repositories.UserRepository;
import java.util.Optional;

public class UserValidator {

  private final UserRepository userRepository;

  public UserValidator(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void validate(final User user) {
    validateExistence(user.getEmail());
  }

  private void validateExistence(final Email email) {
    final Optional<User> duplicatedUser = userRepository.findByEmail(email);
    if (duplicatedUser.isPresent()) {
      throw new DuplicateInstanceException("User already registered");
    }
  }

}
