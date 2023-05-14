Feature: Everything login related

  Scenario: Log in with valid user
    Given I have a valid login object with user "user" and password "password"
    When I call the application login endpoint
    Then I receive a token