package com.santiagovl.dddpill.legacy.rest;

import com.santiagovl.dddpill.legacy.domain.model.user.User;
import com.santiagovl.dddpill.legacy.domain.model.user.repositories.UserRepository;
import com.santiagovl.dddpill.legacy.domain.services.UserValidator;
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

  private final UserValidator userValidator;

  private final UserRepository userRepository;

  private final UserRestConverter userRestConverter;

  @PostMapping
  ResponseEntity<String> registerUser(@RequestBody RestUser restUser) {
    final User user = userRestConverter.convert(restUser);

    userValidator.validate(user);

    final User createdUser = userRepository.save(user);

    return ResponseEntity.ok(createdUser.getId());
  }

}
