package com.santiagovl.dddpill.domain.events;

import static java.time.Instant.now;

import java.time.Instant;
import java.util.UUID;

public abstract class Event {

  private final String id;

  private final Object payload;

  private final Instant happenedAt;

  protected Event(final Object payload) {
    this.id = UUID.randomUUID().toString();
    this.payload = payload;
    this.happenedAt = now();
  }

  public String getId() {
    return id;
  }

  public Object getPayload() {
    return payload;
  }

  public Instant getHappenedAt() {
    return happenedAt;
  }
}
