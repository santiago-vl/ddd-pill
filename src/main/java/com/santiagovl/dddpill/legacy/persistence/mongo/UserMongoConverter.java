package com.santiagovl.dddpill.legacy.persistence.mongo;

import static com.santiagovl.dddpill.legacy.domain.model.user.User.UserBuilder.userBuilder;

import com.google.common.base.Converter;
import com.santiagovl.dddpill.legacy.domain.model.user.Email;
import com.santiagovl.dddpill.legacy.domain.model.user.Password;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import com.santiagovl.dddpill.legacy.domain.model.user.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserMongoConverter extends Converter<User, MongoUser> {

  @Override
  protected MongoUser doForward(final User user) {
    return MongoUser.builder()
        .email(user.getEmail().getEmail())
        .password(user.getPassword().getPassword())
        .build();
  }

  @Override
  protected User doBackward(final MongoUser mongoUser) {
    final UserId id = new UserId(mongoUser.getId());
    final Email email = new Email(mongoUser.getEmail());
    final Password password = new Password(mongoUser.getPassword());
    return userBuilder(email, password).id(id).build();
  }

}
