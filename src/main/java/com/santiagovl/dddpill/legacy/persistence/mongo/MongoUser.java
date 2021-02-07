package com.santiagovl.dddpill.legacy.persistence.mongo;

import static java.time.Instant.now;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
public final class MongoUser {

  @Id
  private String id;

  private String email;

  private String password;

  private Metadata metadata;

  @Data
  public class Metadata {

    private Instant createdAt = now();
  }

  @Builder
  public MongoUser(final String id, final String email, final String password) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.metadata = new Metadata();
  }
}
