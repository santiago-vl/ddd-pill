package com.santiagovl.dddpill.legacy.rest;

import static org.apache.logging.log4j.util.Strings.isBlank;

import com.santiagovl.dddpill.legacy.User;
import com.santiagovl.dddpill.legacy.exceptions.DuplicateInstanceException;
import com.santiagovl.dddpill.legacy.exceptions.InvalidParameterException;
import com.santiagovl.dddpill.legacy.persistence.email.UserEmailRepository;
import com.santiagovl.dddpill.legacy.persistence.mongo.UserRepository;
import com.santiagovl.dddpill.legacy.persistence.spam.UserSpamRepository;
import java.util.Optional;
import java.util.regex.Pattern;
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

  private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

  private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

  private final UserRepository userRepository;

  private final UserEmailRepository emailSender;

  private final UserSpamRepository userSpamRepository;

  private final UserRestConverter userRestConverter;

  @PostMapping
  ResponseEntity<String> registerUser(@RequestBody RestUser restUser) {

    final User user = userRestConverter.convert(restUser);

    // Validate mandatory fields
    if (isBlank(user.getEmail()) || isBlank(user.getPassword())) {
      throw new InvalidParameterException("Email and password are required");
    }

    // Validate email
    final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    final boolean isValidEmail = emailPattern.matcher(user.getEmail()).matches();
    if (!isValidEmail) {
      throw new InvalidParameterException("Invalid email");
    }

    // Validate password
    final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    final boolean isValidPassword = passwordPattern.matcher(user.getPassword()).matches();
    if (!isValidPassword) {
      throw new InvalidParameterException("Invalid password");
    }

    // Validate user existence
    final Optional<User> duplicatedUser = userRepository.findByEmail(user.getEmail());
    if (duplicatedUser.isPresent()) {
      throw new DuplicateInstanceException("User already registered");
    }

    // Save user
    final User createdUser = userRepository.save(user);

    // Send a verification code via email
    emailSender.send(createdUser);

    // Store user email in a spam system
    userSpamRepository.register(createdUser);

    return ResponseEntity.ok(createdUser.getId());
  }

}
