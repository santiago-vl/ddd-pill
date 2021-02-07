package com.santiagovl.dddpill.legacy.persistence.spam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpamConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
