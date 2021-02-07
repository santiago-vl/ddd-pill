package com.santiagovl.dddpill.legacy.persistence;

import com.santiagovl.dddpill.legacy.domain.model.user.User;
import com.santiagovl.dddpill.legacy.domain.model.user.repositories.UserRepository;
import com.santiagovl.dddpill.legacy.persistence.email.UserJavaxEmailSender;
import com.santiagovl.dddpill.legacy.persistence.mongo.UserMongoRepository;
import com.santiagovl.dddpill.legacy.persistence.spam.UserRestSpamRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InfrastructureUserRepository implements UserRepository {

  private final UserMongoRepository userMongoRepository;

  private final UserJavaxEmailSender userJavaxEmailSender;

  private final UserRestSpamRepository userRestSpamRepository;

  @Override
  public User save(User user) {
    userJavaxEmailSender.send(user);
    userRestSpamRepository.register(user);
    return userMongoRepository.save(user);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userMongoRepository.findByEmail(email);
  }
}
