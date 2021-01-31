package com.santiagovl.dddpill.legacy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

  private String email;

  private String password;

  public MongoUser toMongoDocument() {
    return MongoUser.builder()
        .email(email)
        .password(password)
        .build();
  }

}
