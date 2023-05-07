Feature: All Guitars

  Scenario: Get all guitars
    Given When the endpoint "guitars" is available for method "GET"
    When I retrieve all guitars
    Then I should have a list of 3 guitars