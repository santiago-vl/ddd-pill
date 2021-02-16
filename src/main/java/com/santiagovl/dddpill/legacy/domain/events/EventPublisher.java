package com.santiagovl.dddpill.legacy.domain.events;

import java.util.List;

public interface EventPublisher {

  void publish(List<Event> event);

}
