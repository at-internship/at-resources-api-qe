@Regression
Feature: at-resources get operation

    @Get1
  Scenario: Get all the stories registered in the Resources-API database
    Given I prepare the get operation for resources api
    When I call get request to api
    Then The status code should be 200
    And I validate the response for get all stories