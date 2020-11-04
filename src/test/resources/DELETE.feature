@DeleteTests
Feature: at-resources DELETE operation

  #Happy Path
  @D1
  Scenario: Delete a story using the correct id 
    Given I have an existent id
    When I delete a existent story on the api
    Then The API should indicate that the story was deleted with a 204 status code
    	And I verify in the database if the story donesnt exist
	@D2
  Scenario: Delete a story using an incorrect id 
    Given I have a non existent id
    When I delete a existent story on the api
    Then The API should indicate that the story was not found with a 404 status code