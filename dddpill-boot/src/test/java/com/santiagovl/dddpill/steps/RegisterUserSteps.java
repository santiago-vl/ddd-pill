package com.santiagovl.dddpill.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUserSteps {

  @Given("^the email \"([^\"]*)\" is selected$$")
  public void theEmailIsSelected(final String emailInput) {
  }

  @Given("the password {string} is selected")
  public void thePasswordIsSelected(final String passwordInput) {
  }

  @Given("a registered user with email {string}")
  public void aRegisteredUserWithEmail(final String emailInput) {
  }

  @When("^I register the user$")
  public void iRegisterTheUser() {
  }

  @Then("the user is registered")
  public void theUserIsRegistered() {
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

  @Then("a verification code is sent to {string}")
  public void aVerificationCodeIsSentTo(final String email) {
  }

  @Then("{string} is registered in spam system")
  public void isRegisteredInSpamSystem(final String email) {
  }
}