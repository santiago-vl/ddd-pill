package com.santiagovl.dddpill.legacy.events;

import static java.util.stream.Collectors.toList;

import com.santiagovl.dddpill.legacy.domain.events.Event;
import com.santiagovl.dddpill.legacy.domain.events.EventPublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FakeEventPublisher implements EventPublisher {

  private final EventSerializer eventSerializer;

  @Override
  public void publish(final List<Event> event) {
    final List<String> serializedEvents = event.stream()
        .map(eventSerializer::serialize)
        .collect(toList());

    // publish(serializedEvents);
  }
}
