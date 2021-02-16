package com.santiagovl.dddpill.infrastructure.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestUser {

  private final String email;

  private final String password;

}
