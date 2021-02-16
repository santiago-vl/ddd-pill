Feature: Registers a user
  As a user
  I want to register in the system
  To access the system with my credentials

  # Happy path scenarios

  Scenario: registers a user
    Given the email "foo@bar.com" is selected
    And the password "kI3!SkX2eF1?" is selected
    When I register the user
    Then the user is registered

  # Unhappy paths scenarios

  Scenario: fails with empty name
    Given the email "" is selected
    And the password "kI3!SkX2eF1?" is selected
    When I register the user
    Then the user is not registered
    And a validation error is thrown

  Scenario: fails with empty password
    Given the email "foo@bar.com" is selected
    And the password "" is selected
    When I register the user
    Then the user is not registered
    And a validation error is thrown

  Scenario Outline: fails with invalid email
    Given the email "<emailInput>" is selected
    And the password "password" is selected
    When I register the user
    Then the user is not registered
    And a validation error is thrown

    Examples:
      | emailInput          |
      | foo@barcom          |
      | foobar.com          |
      | foo@bar.com OR 1=1; |

  # Password policy:
  #   * At least 8 chars
  #   * Contains at least one digit
  #   * Contains at least one lower alpha char and one upper alpha char
  #   * Contains at least one char within a set of special chars (@#%$^ etc.)
  #   * Does not contain space, tab, etc...
  Scenario Outline: fails with invalid password
    Given the email "foo@bar.com" is selected
    And the password "<passwordInput>" is selected
    When I register the user
    Then the user is not registered
    And a validation error is thrown

    Examples:
      | passwordInput        |
      | 777777               |
      | CristianoRonaldo     |
      | CristianoRonaldoCR7  |
      | Cristiano Ronaldo CR |
      | cr7!!!!!!!!          |

  Scenario: fails when user is already registered
    Given a registered user with email "registered-user@bar.com"
    And the email "registered-user@bar.com" is selected
    And the password "kI3!SkX2eF1?" is selected
    When I register the user
    Then the user is not registered
    And a duplication error is thrown

