package com.santiagovl.dddpill.legacy.domain.model.user.repositories;

import com.santiagovl.dddpill.legacy.domain.model.user.User;
import java.util.Optional;

public interface UserRepository {

  User save(User user);

  Optional<User> findByEmail(String email);

}
