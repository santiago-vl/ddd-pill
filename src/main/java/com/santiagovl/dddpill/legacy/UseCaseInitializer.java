package com.santiagovl.dddpill.legacy;

import com.santiagovl.dddpill.legacy.application.user.RegisterUser;
import com.santiagovl.dddpill.legacy.domain.model.user.repositories.UserRepository;
import com.santiagovl.dddpill.legacy.domain.services.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseInitializer {

  @Autowired
  private UserRepository userRepository;

  @Bean
  public UserValidator userValidator() {
    return new UserValidator(userRepository);
  }

  @Bean
  public RegisterUser registerUser(final UserValidator userValidator) {
    return new RegisterUser(userValidator, userRepository);
  }

}
