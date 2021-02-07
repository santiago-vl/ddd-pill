package com.santiagovl.dddpill.legacy.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestUser {

  private final String email;

  private final String password;

}
