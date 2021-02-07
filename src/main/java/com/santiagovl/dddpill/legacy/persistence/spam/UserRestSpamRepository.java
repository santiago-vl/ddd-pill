package com.santiagovl.dddpill.legacy.persistence.spam;

import com.santiagovl.dddpill.legacy.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserRestSpamRepository implements UserSpamRepository {

  private final RestTemplate restTemplate;

  @Override
  public void register(final User user) {
    final HttpEntity<String> body = new HttpEntity<>(user.getEmail());
    restTemplate.postForEntity("http://www.spam.com/email", body, String.class);
  }

}
