package com.santiagovl.dddpill.legacy.events;

import static com.santiagovl.dddpill.legacy.domain.model.user.User.UserBuilder.userBuilder;

import com.santiagovl.dddpill.legacy.domain.events.Event;
import com.santiagovl.dddpill.legacy.domain.events.UserSaved;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import com.santiagovl.dddpill.legacy.persistence.email.UserJavaxEmailSender;
import com.santiagovl.dddpill.legacy.persistence.spam.UserRestSpamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSavedSubscriber {

  private final UserJavaxEmailSender userJavaxEmailSender;

  private final UserRestSpamRepository userRestSpamRepository;

  private final EventSerializer eventSerializer;

  void consume(final String payload) {
    final Event event = eventSerializer.deserialize(payload);
    if (event instanceof UserSaved) {
      final User user = (User) event.getPayload();
      userJavaxEmailSender.send(user);
      userRestSpamRepository.register(user);
    }
  }

}
