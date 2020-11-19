@Regression
Feature: at-resources put operation
	
	@Put1
	Scenario: Update story using an existing id
  Given I get a story to update
  And I have a priority to update
  And I have a name to update
  And I have a description to update
  And I have a acceptanceCriteria to update
  And I have a storyPoints to update
  And I have a progress to update
  And I have a start date as current date to update
  And I have an due date to update
  And I have an create date to update
  And I have a status to update
  When I update sprint using PUT operation
  Then I should retrieve an OK

#MDKT-22 Validations    	
	@Put2
  Scenario: Update a story with a priority field in Lowercase.
    	Given I get a story to update
        And I have a lowercase priority to update
        And I have a name to update
        And I have a description to update
        And I have a acceptanceCriteria to update
        And I have a storyPoints to update
        And I have a progress to update
        And I have a start date as current date to update
        And I have an due date to update
        And I have an create date to update
        And I have a status to update
    When I update sprint using PUT operation
    Then The API should indicate that the story was not updated with a 400 status code
    	And The error message should give the correct string

	@Put3
	Scenario: Update a story with a priority field in Uppercase.
		Given I get a story to update
		And I have a uppercase priority to update
		And I have a name to update
		And I have a description to update
		And I have a acceptanceCriteria to update
		And I have a storyPoints to update
		And I have a progress to update
		And I have a start date as current date to update
		And I have an due date to update
		And I have an create date to update
		And I have a status to update
		When I update sprint using PUT operation
		Then The API should indicate that the story was not updated with a 400 status code
		And The error message should give the correct string

	@Put4
	Scenario: Update a story with a description field empty.
		Given I get a story to update
		And I have a priority to update
		And I have a name to update
		And I have a description as empty to update
		And I have a acceptanceCriteria to update
		And I have a storyPoints to update
		And I have a progress to update
		And I have a start date as current date to update
		And I have an due date to update
		And I have an create date to update
		And I have a status to update
		When I update sprint using PUT operation
		Then The API should indicate that the story was not updated with a 400 status code
		And The error message should give the correct string

	@Put5
	Scenario: Update a story with an out of range fibonacci number in the story points.
		Given I get a story to update
		And I have a priority to update
		And I have a name to update
		And I have a description to update
		And I have a acceptanceCriteria to update
		And I have story points out of accepted range to update
		And I have a progress to update
		And I have a start date as current date to update
		And I have an due date to update
		And I have an create date to update
		And I have a status to update
		When I update sprint using PUT operation
		Then The API should indicate that the story was not updated with a 400 status code
		And The error message should give the correct string

	@Put6
	Scenario: Update a story with two as value in the story points field.
		Given I get a story to update
		And I have a priority to update
		And I have a name to update
		And I have a description to update
		And I have a acceptanceCriteria to update
		And I have story points with two as value to update
		And I have a progress to update
		And I have a start date as current date to update
		And I have an due date to update
		And I have an create date to update
		And I have a status to update
		When I update sprint using PUT operation
		Then The API should indicate that the story was not updated with a 200 status code
			And I should retrieve an OK

	@Put7
	Scenario: Update a story with eight as value in the story points field.
		Given I get a story to update
		And I have a priority to update
		And I have a name to update
		And I have a description to update
		And I have a acceptanceCriteria to update
		And I have story points with eight as value to update
		And I have a progress to update
		And I have a start date as current date to update
		And I have an due date to update
		And I have an create date to update
		And I have a status to update
		When I update sprint using PUT operation
		Then The API should indicate that the story was not updated with a 200 status code
		And I should retrieve an OK