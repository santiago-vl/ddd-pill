package com.santiagovl.dddpill.legacy.application.user;

import com.santiagovl.dddpill.legacy.domain.events.EventPublisher;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import com.santiagovl.dddpill.legacy.domain.model.user.repositories.UserRepository;
import com.santiagovl.dddpill.legacy.domain.services.UserValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUser {

  private final UserValidator userValidator;

  private final UserRepository userRepository;

  private final EventPublisher eventPublisher;

  public Object register(final User user) {
    userValidator.validate(user);

    final User savedUser = userRepository.save(user);

    savedUser.isSaved();
    eventPublisher.publish(savedUser.getEvents());

    return savedUser;
  }
}
