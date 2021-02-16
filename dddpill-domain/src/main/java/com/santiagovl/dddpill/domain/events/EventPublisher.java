package com.santiagovl.dddpill.domain.events;

import java.util.List;

public interface EventPublisher {

  void publish(List<Event> event);

}
