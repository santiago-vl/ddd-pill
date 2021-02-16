package com.santiagovl.dddpill.domain.model.user.repositories;

import com.santiagovl.dddpill.domain.model.user.Email;
import com.santiagovl.dddpill.domain.model.user.User;
import java.util.Optional;

public interface UserRepository {

  User save(User user);

  Optional<User> findByEmail(Email email);

}
