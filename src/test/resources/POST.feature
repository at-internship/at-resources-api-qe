@PostTests
Feature: at-resources POST operation

  #Happy Path
  @P1
  Scenario: Create a new story with correct values
    Given I have a priority asigned
    	And I have a story name
    	And I have a story description
    	And I have acceptance criteria
    	And I have story points asigned
    	And I have a progress
    	And I have the start date
    	And I have the due date
    	And I have the create date
    	And I have a status
    	And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was created with a 201 status code
    	And The API should return a random id
    	And I verify in the database if the story exist