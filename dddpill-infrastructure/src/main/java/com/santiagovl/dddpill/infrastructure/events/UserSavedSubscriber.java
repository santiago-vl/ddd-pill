package com.santiagovl.dddpill.infrastructure.events;

import com.santiagovl.dddpill.domain.events.Event;
import com.santiagovl.dddpill.domain.events.UserSaved;
import com.santiagovl.dddpill.domain.model.user.User;
import com.santiagovl.dddpill.infrastructure.persistence.email.UserJavaxEmailSender;
import com.santiagovl.dddpill.infrastructure.persistence.spam.UserRestSpamRepository;
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
