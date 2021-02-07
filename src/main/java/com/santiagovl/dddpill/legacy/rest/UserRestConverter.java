package com.santiagovl.dddpill.legacy.rest;

import static com.santiagovl.dddpill.legacy.domain.model.user.User.UserBuilder.userBuilder;

import com.google.common.base.Converter;
import com.santiagovl.dddpill.legacy.domain.model.user.Email;
import com.santiagovl.dddpill.legacy.domain.model.user.Password;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserRestConverter extends Converter<RestUser, User> {

  @Override
  protected User doForward(final RestUser restUser) {
    final Email email = new Email(restUser.getEmail());
    final Password password = new Password(restUser.getPassword());
    return userBuilder(email, password).build();
  }

  @Override
  protected RestUser doBackward(final User user) {
    // Unsupported operation
    return null;
  }

}
