Feature: In order to test SprintCollection API
	I going to update  Sprints using PUT operation for sprintsAPI
	
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