package com.santiagovl.dddpill.domain.model.user;

import static java.time.Instant.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import com.santiagovl.dddpill.domain.events.Event;
import com.santiagovl.dddpill.domain.events.UserSaved;
import com.santiagovl.dddpill.domain.exceptions.InvalidParameterException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {

  private final UserId id;

  private Email email;

  private Password password;

  private final Instant createdAt;

  private final List<Event> events;

  private User(final UserId id, final Email email, final Password password, final Instant createdAt) {
    this.id = id;
    changeEmail(email);
    changePassword(password);
    this.createdAt = defaultIfNull(createdAt, now());
    events = new ArrayList<>();
  }

  public void changeEmail(final Email email) {
    if (email == null) {
      throw new InvalidParameterException("Email are required");
    }
    this.email = email;
  }

  public void changePassword(final Password password) {
    if (password == null) {
      throw new InvalidParameterException("Password are required");
    }
    this.password = password;
  }

  public void isCreated() {
    events.add(new UserSaved(this));
  }

  public UserId getId() {
    return id;
  }

  public Email getEmail() {
    return email;
  }

  public Password getPassword() {
    return password;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public List<Event> getEvents() {
    return events;
  }

  public static final class UserBuilder {

    private UserId id;

    private Email email;

    private Password password;

    private Instant createdAt;

    public UserBuilder(final UserId id, final Email email, final Password password, final Instant createdAt) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.createdAt = createdAt;
    }

    public static UserBuilder userBuilder(final Email email, final Password password) {
      return new UserBuilder(null, email, password, null);
    }

    public UserBuilder id(final UserId id) {
      this.id = id;
      return this;
    }

    public UserBuilder email(final Email email) {
      this.email = email;
      return this;
    }

    public UserBuilder password(final Password password) {
      this.password = password;
      return this;
    }

    public UserBuilder createdAt(final Instant createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public User build() {
      return new User(id, email, password, createdAt);
    }
  }
}
