package com.santiagovl.dddpill.legacy.rest;

import com.santiagovl.dddpill.legacy.application.user.RegisterUser;
import com.santiagovl.dddpill.legacy.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public final class UserController {

  private final UserRestConverter userRestConverter;

  private final RegisterUser registerUser;

  @PostMapping
  ResponseEntity<String> registerUser(final @RequestBody RestUser restUser) {
    final User user = userRestConverter.convert(restUser);
    final User createdUser = (User) registerUser.register(user);
    return ResponseEntity.ok(createdUser.getId());
  }

}

