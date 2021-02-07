package com.santiagovl.dddpill.legacy.persistence.mongo;

import com.santiagovl.dddpill.legacy.User;
import java.util.Optional;

public interface UserRepository {

  User save(User user);

  Optional<User> findByEmail(String name);

}
