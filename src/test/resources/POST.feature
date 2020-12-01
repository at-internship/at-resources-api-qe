@Regression
Feature: at-resources POST operation

  #Happy Path
  @Post1
  Scenario: Create a new story with correct values
    Given I create a request as follow
      | priority           | random |
      | name               | random |
      | description        | random |
      | acceptanceCriteria | random |
      | storyPoints        | random |
      | progress           | random |
      | startDate          | random |
      | dueDate            | random |
      | createDate         | random |
      | status             | random |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was created with a 201 status code
    And The API should return a random id
    And I verify in the database if the story exist
#MDKT-22 Validations
  @Post2
  Scenario: Send a correct keyword in Lowercase in the priority field.
    Given I create a request as follow
      | priority           | lowercase |
      | name               | random    |
      | description        | random    |
      | acceptanceCriteria | random    |
      | storyPoints        | random    |
      | progress           | random    |
      | startDate          | random    |
      | dueDate            | random    |
      | createDate         | random    |
      | status             | random    |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post3
  Scenario: Send a correct keyword in Uppercase in the priority field.
    Given I create a request as follow
      | priority           | uppercase |
      | name               | random    |
      | description        | random    |
      | acceptanceCriteria | random    |
      | storyPoints        | random    |
      | progress           | random    |
      | startDate          | random    |
      | dueDate            | random    |
      | createDate         | random    |
      | status             | random    |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post4
  Scenario: Send  the description field empty
    Given I create a request as follow
      | priority           | random |
      | name               | random |
      | description        | empty  |
      | acceptanceCriteria | random |
      | storyPoints        | random |
      | progress           | random |
      | startDate          | random |
      | dueDate            | random |
      | createDate         | random |
      | status             | random |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post5
  Scenario: Send an out of range fibonacci number in the story points field
    Given I create a request as follow
      | priority           | random    |
      | name               | random    |
      | description        | random    |
      | acceptanceCriteria | random    |
      | storyPoints        | fibonacci |
      | progress           | random    |
      | startDate          | random    |
      | dueDate            | random    |
      | createDate         | random    |
      | status             | random    |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post6
  Scenario: Send a two as value in the story points field
    Given I create a request as follow
      | priority           | random |
      | name               | random |
      | description        | random |
      | acceptanceCriteria | random |
      | storyPoints        | two    |
      | progress           | random |
      | startDate          | random |
      | dueDate            | random |
      | createDate         | random |
      | status             | random |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was created with a 201 status code
    And The API should return a random id
    And I verify in the database if the story exist

  @Post7
  Scenario: Send a eight as value in the story points field
    Given I create a request as follow
      | priority           | random |
      | name               | random |
      | description        | random |
      | acceptanceCriteria | random |
      | storyPoints        | eight  |
      | progress           | random |
      | startDate          | random |
      | dueDate            | random |
      | createDate         | random |
      | status             | random |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was created with a 201 status code
    And The API should return a random id
    And I verify in the database if the story exist

  @Post8
  Scenario: Send a random id in the id field
    Given I create a request as follow
      | id                 | random |
      | priority           | random |
      | name               | random |
      | description        | random |
      | acceptanceCriteria | random |
      | storyPoints        | random |
      | progress           | random |
      | startDate          | random |
      | dueDate            | random |
      | createDate         | random |
      | status             | random |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was created with a 201 status code
    And The API should return a different id
    And I verify in the database if the story exist


  @Post9
  Scenario: Send letters in the status field
    Given I create a request as follow
      | priority           | random  |
      | name               | random  |
      | description        | random  |
      | acceptanceCriteria | random  |
      | storyPoints        | random  |
      | progress           | random  |
      | startDate          | random  |
      | dueDate            | random  |
      | createDate         | random  |
      | status             | letters |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post10
  Scenario: Send alphanumeric value in the status field
    Given I create a request as follow
      | priority           | random       |
      | name               | random       |
      | description        | random       |
      | acceptanceCriteria | random       |
      | storyPoints        | random       |
      | progress           | random       |
      | startDate          | random       |
      | dueDate            | random       |
      | createDate         | random       |
      | status             | alphanumeric |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post11
  Scenario: Send special characters in the status field
    Given I create a request as follow
      | priority           | random            |
      | name               | random            |
      | description        | random            |
      | acceptanceCriteria | random            |
      | storyPoints        | random            |
      | progress           | random            |
      | startDate          | random            |
      | dueDate            | random            |
      | createDate         | random            |
      | status             | specialCharacters |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post12
  Scenario: Send a negative number in the status field
    Given I create a request as follow
      | priority           | random   |
      | name               | random   |
      | description        | random   |
      | acceptanceCriteria | random   |
      | storyPoints        | random   |
      | progress           | random   |
      | startDate          | random   |
      | dueDate            | random   |
      | createDate         | random   |
      | status             | negative |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string

  @Post13
  Scenario: Send a value greater than one in the status field
    Given I create a request as follow
      | priority           | random  |
      | name               | random  |
      | description        | random  |
      | acceptanceCriteria | random  |
      | storyPoints        | random  |
      | progress           | random  |
      | startDate          | random  |
      | dueDate            | random  |
      | createDate         | random  |
      | status             | greater |
    And i prepare the resource
    When I create a new story on the api
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should have the correct string