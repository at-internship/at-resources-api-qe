package com.stepdefinitions;

import static org.junit.Assert.assertEquals;

import com.globalClasses.ApiPaths;
import com.globalClasses.ApiTools;
import com.globalClasses.BasicSecurityUtil;
import com.globalClasses.MongoDBUtils;
import com.globalClasses.TestValues;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DELETE {
	private BasicSecurityUtil base;
	TestValues val = new TestValues();
	String id = "";

	public DELETE(BasicSecurityUtil base) {
		this.base = base;
	}

	@Given("I have an existent id")
	public void i_have_an_existent_id() {
		id = MongoDBUtils.getRandomID("TEST", "at-resources-db", "stories");
	}

	@Given("I have a non existent id")
	public void i_have_a_non_existent_id() {
		id = val.randomID();
	}

	@When("I delete a existent story on the api")
	public void i_delete_a_existent_story_on_the_api() {
		base.apiResource = ApiPaths.RESOURCES_API_JAVA + "/" + id;
		base.ServiceApi = new ApiTools();
		base.response = base.ServiceApi.deleteMethod(base.apiResource);
	}

	@Then("The API should indicate that the story was deleted with a {int} status code")
	public void the_API_should_indicate_that_the_story_was_deleted_with_a_status_code(int statusCode) {
		int result = Integer.parseInt(base.response.getStatusCode().toString().substring(0, 3));
		assertEquals(statusCode, result);
	}

	@Then("The API should indicate that the story was not found with a 404 status code")
	public void the_API_should_indicate_that_the_story_was_not_found_with_a_status_code(int statusCode) {
		int result = Integer.parseInt(base.response.getStatusCode().toString().substring(0, 3));
		assertEquals(statusCode, result);
	}

	@Then("I verify in the database if the story donesnt exist")
	public void i_verify_in_the_database_if_the_story_donesnt_exist() {
		assert false == MongoDBUtils.existID("TEST", "at-resources-db", "stories", id);
	}
}
