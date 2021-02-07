package com.santiagovl.dddpill.legacy.persistence.email;

import com.santiagovl.dddpill.legacy.User;

public interface UserEmailRepository {

  void send(User user);

}
