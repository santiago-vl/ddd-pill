package com.santiagovl.bddpill.rest;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

  @PostMapping
  public ResponseEntity<UserRestDTO> registerUser(@RequestBody UserRestDTO userInput) {
    return ok(UserRestDTO.builder()
        .email(userInput.getEmail())
        .password(userInput.getPassword())
        .build());
  }

}
