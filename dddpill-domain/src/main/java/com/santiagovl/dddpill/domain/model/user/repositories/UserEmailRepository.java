package com.santiagovl.dddpill.domain.model.user.repositories;

import com.santiagovl.dddpill.domain.model.user.User;

public interface UserEmailRepository {

  void send(User user);

}
