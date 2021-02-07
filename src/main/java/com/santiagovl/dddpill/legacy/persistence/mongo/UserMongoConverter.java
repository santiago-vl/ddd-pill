package com.santiagovl.dddpill.legacy.persistence.mongo;

import com.google.common.base.Converter;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMongoConverter extends Converter<User, MongoUser> {

  @Override
  protected MongoUser doForward(final User user) {
    return MongoUser.builder()
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }

  @Override
  protected User doBackward(final MongoUser mongoUser) {
    return User.builder()
        .id(mongoUser.getId())
        .email(mongoUser.getEmail())
        .password(mongoUser.getPassword())
        .createdAt(mongoUser.getMetadata().getCreatedAt())
        .build();
  }

}
