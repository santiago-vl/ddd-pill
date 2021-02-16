package com.santiagovl.dddpill.domain.model.user;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.santiagovl.dddpill.domain.exceptions.InstanceValidationException;

public class UserId {

  private final String identifier;

  public UserId(final String identifier) {
    validateId(identifier);
    this.identifier = identifier;
  }

  private void validateId(final String identifier) {
    if (isBlank(identifier)) {
      throw new InstanceValidationException("User identifier cannot be blank");
    }
  }

  public String getIdentifier() {
    return identifier;
  }
}
