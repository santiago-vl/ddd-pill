package com.santiagovl.bddpill.rest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRestDTO {

  private final String email;

  private final String password;

}
