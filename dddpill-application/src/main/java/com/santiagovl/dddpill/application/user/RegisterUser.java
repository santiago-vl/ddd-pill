package com.santiagovl.dddpill.application.user;

import com.santiagovl.dddpill.domain.events.EventPublisher;
import com.santiagovl.dddpill.domain.model.user.User;
import com.santiagovl.dddpill.domain.model.user.repositories.UserRepository;
import com.santiagovl.dddpill.domain.services.UserValidator;

public class RegisterUser {

  private final UserValidator userValidator;

  private final UserRepository userRepository;

  private final EventPublisher eventPublisher;

  public RegisterUser(final UserValidator userValidator, final UserRepository userRepository, final EventPublisher eventPublisher) {
    this.userValidator = userValidator;
    this.userRepository = userRepository;
    this.eventPublisher = eventPublisher;
  }

  public Object register(final User user) {
    userValidator.validate(user);

    final User createdUser = userRepository.save(user);

    createdUser.isCreated();
    eventPublisher.publish(createdUser.getEvents());

    return userRepository.save(user);
  }
}
