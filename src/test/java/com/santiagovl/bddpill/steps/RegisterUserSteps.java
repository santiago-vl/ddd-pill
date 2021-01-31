package com.santiagovl.bddpill.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.santiagovl.bddpill.rest.UserController;
import com.santiagovl.bddpill.rest.UserRestDTO;
import com.santiagovl.bddpill.steps.StepsConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class RegisterUserSteps extends StepsConfiguration {

  // Use RestTemplate instead of inject the Controller to be more exhaustive
  private final UserController userController;

  private String emailInput;

  private String passwordInput;

  private ResponseEntity<UserRestDTO> response;

  private Exception exception;

  @Before
  public void init() {
    emailInput = null;
    passwordInput = null;
    response = null;
    exception = null;
  }

  // Regular expression
  @Given("^the email \"([^\"]*)\" is selected$$")
  public void theEmailIsSelected(final String emailInput) {
    this.emailInput = emailInput;
  }

  // Cucumber expression
  @Given("the password {string} is selected")
  public void thePasswordIsSelected(final String passwordInput) {
    this.passwordInput = passwordInput;
  }

  @Given("a registered user with email {string}")
  public void aRegisteredUserWithEmail(final String emailInput) {
  }

  @When("^I register the user$")
  public void iRegisterTheUser() {
    final UserRestDTO userRestDTO = UserRestDTO.builder()
        .email(this.emailInput)
        .password(this.passwordInput)
        .build();
    try {
      response = userController.registerUser(userRestDTO);
    } catch (final Exception exception) {
      this.exception = exception;
    }
  }

  @Then("the user is registered")
  public void theUserIsRegistered() {
    assertThat(this.exception).isNull();
    assertThat(response.getBody().getEmail()).isEqualTo(this.emailInput);
    assertThat(response.getBody().getPassword()).isEqualTo(this.passwordInput);
  }

  @Then("the user is not registered")
  public void theUserIsNotRegistered() {
  }

  @Then("a validation error is thrown")
  public void aValidationErrorIsThrown() {
  }

  @Then("a duplication error is thrown")
  public void aDuplicationErrorIsThrown() {
  }
}
