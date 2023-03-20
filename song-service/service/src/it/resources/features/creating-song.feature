Feature: Testing a REST API
  Scenario: Data Upload to a web service
    When user creates new song
    Then the client receives status code of 200