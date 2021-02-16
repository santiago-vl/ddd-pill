package com.santiagovl.dddpill.domain.events;

import com.santiagovl.dddpill.domain.model.user.User;

public class UserSaved extends Event {

  public UserSaved(final User user) {
    super(user);
  }

}
