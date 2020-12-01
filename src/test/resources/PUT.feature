@Regression
Feature: at-resources put operation

  Background: I have a story to update
    Given I get a story to update

  @Put1
  Scenario: Update story using an existing id
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then I should retrieve an OK

#MDKT-22 Validations
  @Put2
  Scenario: Update a story with a priority field in Lowercase.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not updated with a 400 status code
    And The error message should give the correct string

  @Put3
  Scenario: Update a story with a priority field in Uppercase.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not updated with a 400 status code
    And The error message should give the correct string

  @Put4
  Scenario: Update a story with a description field empty.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not updated with a 400 status code
    And The error message should give the correct string

  @Put5
  Scenario: Update a story with an out of range fibonacci number in the story points.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not updated with a 400 status code
    And The error message should give the correct string

  @Put6
  Scenario: Update a story with two as value in the story points field.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not updated with a 200 status code
    And I should retrieve an OK

  @Put7
  Scenario: Update a story with eight as value in the story points field.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not updated with a 200 status code
    And I should retrieve an OK

  @Put8
  Scenario: Update a story with letters as value in the status field.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should give the correct string

  @Put9
  Scenario: Update a story with alphanumeric as value in the status field.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should give the correct string

  @Put10
  Scenario: Update a story with specialCharacters as value in the status field.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should give the correct string

  @Put11
  Scenario: Update a story with a negative number as value in the status field.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should give the correct string

  @Put12
  Scenario: Update a story with a greater number than 1 as value in the status field.
    Given I create a request to update as follow
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
    When I update sprint using PUT operation
    Then The API should indicate that the story was not created with a 400 status code
    And The error message should give the correct string