package com.santiagovl.dddpill.legacy;

import static org.apache.logging.log4j.util.Strings.isBlank;

import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public final class UserController {

  private static final String USERS_COLLECTION_NAME = "users";

  private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

  private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

  private final MongoTemplate mongoTemplate;

  private final RestTemplate restTemplate;

  @PostMapping
  ResponseEntity<String> registerUser(@RequestBody User newUser) {
    // Validate mandatory fields
    if (isBlank(newUser.getEmail()) || isBlank(newUser.getPassword())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
    }

    // Validate email
    final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    final boolean isValidEmail = emailPattern.matcher(newUser.getEmail()).matches();
    if (!isValidEmail) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email");
    }

    // Validate password
    final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    final boolean isValidPassword = passwordPattern.matcher(newUser.getPassword()).matches();
    if (!isValidPassword) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password");
    }

    // Validate user existence
    final Criteria emailCriteria = Criteria.where("email").is(newUser.getEmail());
    final Query findUserByEmailQuery = new Query().addCriteria(emailCriteria);
    final Optional<MongoUser> duplicatedUser = mongoTemplate.find(findUserByEmailQuery, MongoUser.class).stream().findAny();
    if (duplicatedUser.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("User already registered");
    }

    // Save user in MongoDb
    final MongoUser mongoUser = mongoTemplate.insert(newUser.toMongoDocument(), USERS_COLLECTION_NAME);

    // Send a verification code via email
    final Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
    final Session session = Session.getDefaultInstance(properties);
    try {
      final MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress("foo@gmail.com"));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(mongoUser.getEmail()));
      message.setSubject("Verification code");
      message.setText(String.format("%06d", new Random().nextInt(mongoUser.getMetadata().getCreatedAt().getNano())));
      // Transport.send(message);
    } catch (final MessagingException e) {
      e.printStackTrace();
    }

    // Store user email in a spam system
    final HttpEntity<String> body = new HttpEntity<>(mongoUser.getEmail());
    restTemplate.postForEntity("http://www.spam.com/email", body, String.class);

    return ResponseEntity.ok(mongoUser.getId());
  }

}
