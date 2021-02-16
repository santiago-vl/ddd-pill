package com.santiagovl.dddpill.infrastructure.persistence;

import com.santiagovl.dddpill.domain.model.user.Email;
import com.santiagovl.dddpill.domain.model.user.User;
import com.santiagovl.dddpill.domain.model.user.repositories.UserRepository;
import com.santiagovl.dddpill.infrastructure.persistence.mongo.UserMongoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InfrastructureUserRepository implements UserRepository {

  private final UserMongoRepository userMongoRepository;

  @Override
  public User save(final User user) {
    return userMongoRepository.save(user);
  }

  @Override
  public Optional<User> findByEmail(final Email email) {
    return userMongoRepository.findByEmail(email);
  }
}
