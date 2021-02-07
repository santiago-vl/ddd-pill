package com.santiagovl.dddpill.legacy.rest;

import com.google.common.base.Converter;
import com.santiagovl.dddpill.legacy.User;
import org.springframework.stereotype.Component;

@Component
public class UserRestConverter extends Converter<RestUser, User> {

  @Override
  protected User doForward(final RestUser restUser) {
    return User.builder()
        .email(restUser.getEmail())
        .password(restUser.getPassword())
        .build();
  }

  @Override
  protected RestUser doBackward(final User user) {
    // Unsupported operation
    return null;
  }

}
