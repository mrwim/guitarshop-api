Feature: Everything to do with guitars

  Scenario: getting all guitars
    Given I log in as user
    When I retrieve all guitars
    Then I get a list of 3 guitars
    And I get http status 200

  Scenario: Create guitar
    Given I log in as admin
    When I provide a guitar with brand name "Fender" and model name "Jaguar"
    Then I get http status 201
    And The price of the guitar is 0

