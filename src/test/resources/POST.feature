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
#MDKT-22 Validations    	
	@P2
  Scenario: Send a correct keyword in Lowercase in the priority field.
    Given I have a lowercase priority asigned
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
    Then The API should indicate that the story was not created with a 400 status code
    	And The error message should have the correct string
    	
  @P3
  Scenario: Send a correct keyword in Uppercase in the priority field.
    Given I have an uppercase priority asigned
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
    Then The API should indicate that the story was not created with a 400 status code
    	And The error message should have the correct string
  
  @P4
  Scenario: Send  the description field empty
    Given I have a priority asigned
    	And I have a story name
    	And I have a story description as empty field
    	And I have acceptance criteria
    	And I have story points asigned
    	And I have a progress
    	And I have the start date
    	And I have the due date
    	And I have the create date
    	And I have a status
    	And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    	And The error message should have the correct string

	@P5
  Scenario: Send an out of range fibonacci number in the story points field
    Given I have a priority asigned
    	And I have a story name
    	And I have a story description
    	And I have acceptance criteria
    	And I have story points out of accepted range
    	And I have a progress
    	And I have the start date
    	And I have the due date
    	And I have the create date
    	And I have a status
    	And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    	And The error message should have the correct string
    
  @P6
  Scenario: Send a two as value in the story points field
    Given I have a priority asigned
    	And I have a story name
    	And I have a story description 
    	And I have acceptance criteria
    	And I have story points with two as value
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
    	
  @P7
  Scenario: Send a eight as value in the story points field
    Given I have a priority asigned
    	And I have a story name
    	And I have a story description 
    	And I have acceptance criteria
    	And I have story points with eight as value
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