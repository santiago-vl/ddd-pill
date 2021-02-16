package com.santiagovl.dddpill.infrastructure;

import com.santiagovl.dddpill.application.user.RegisterUser;
import com.santiagovl.dddpill.domain.events.EventPublisher;
import com.santiagovl.dddpill.domain.model.user.repositories.UserRepository;
import com.santiagovl.dddpill.domain.services.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseInitializer {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Bean
  public UserValidator userValidator() {
    return new UserValidator(userRepository);
  }

  @Bean
  public RegisterUser registerUser(final UserValidator userValidator) {
    return new RegisterUser(userValidator, userRepository, eventPublisher);
  }

}
