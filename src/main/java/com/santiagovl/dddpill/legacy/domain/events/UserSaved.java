package com.santiagovl.dddpill.legacy.domain.events;

import com.santiagovl.dddpill.legacy.domain.model.user.User;

public class UserSaved extends Event {

  public UserSaved(final User user) {
    super(user);
  }

}
