package com.santiagovl.dddpill.legacy.domain.services;

import com.santiagovl.dddpill.legacy.domain.exceptions.DuplicateInstanceException;
import com.santiagovl.dddpill.legacy.domain.model.user.Email;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import com.santiagovl.dddpill.legacy.domain.model.user.repositories.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator {

  private final UserRepository userRepository;

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
