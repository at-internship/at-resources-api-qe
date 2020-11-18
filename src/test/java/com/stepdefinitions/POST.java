package com.stepdefinitions;

import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;

import com.domain.RequestPOST;
import com.globalClasses.ApiPaths;
import com.globalClasses.ApiTools;
import com.globalClasses.BasicSecurityUtil;
import com.globalClasses.MongoDBUtils;
import com.globalClasses.TestValues;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class POST {
	private BasicSecurityUtil base;
	RequestPOST req = new RequestPOST();
	JSONArray jsonResult = new JSONArray();
	TestValues val = new TestValues();

	public POST(BasicSecurityUtil base) {
		this.base = base;
	}
//Happy path
	@Given("I have a priority asigned")
	public void i_have_priority_asigned() {
		req.setPriority(val.randomPriority());
	}
	
	@Given("I have a story name")
	public void i_have_a_story_name() {
		req.setName(val.randomName());
	}

	@Given("I have a story description")
	public void i_have_a_story_description() {
		req.setDescription(val.randomString());
	}

	@Given("I have acceptance criteria")
	public void i_have_acceptance_criteria() {
		req.setACriteria(val.randomString());
	}

	@Given("I have story points asigned")
	public void i_have_story_points_asigned() {
		req.setSPoints(val.randomStoryPoints());
	}

	@Given("I have a progress")
	public void i_have_a_progress() {
		req.setProgress(val.randomProgress());
	}

	@Given("I have the start date")
	public void i_have_the_start_date() {
		req.setStartDate(val.randomStartDate().toString());
	}

	@Given("I have the due date")
	public void i_have_the_due_date() {
		req.setDueDate(val.randomDueDate().toString());
	}

	@Given("I have the create date")
	public void i_have_the_create_date() {
		req.setCreateDate(val.createDate().toString());
	}

	@Given("I have a status")
	public void i_have_a_status() {
		req.setStatus(val.randomStatus());
	}
//Priority validations
	@Given("I have an uppercase priority asigned")
	public void i_have_an_uppercase_priority_asigned() {
		req.setPriority(val.randomPriority().toUpperCase());
	}

	@Given("I have a lowercase priority asigned")
	public void i_have_a_lowercase_priority_asigned() {
		req.setPriority(val.randomPriority().toLowerCase());
	}
//Description validations
	@Given("I have a story description as empty field")
	public void i_have_a_story_description_as_emprty_field() {
		req.setDescription("");
	}
//Story Points validations
	@Given("I have story points out of accepted range")
	public void i_have_story_points_out_of_accepted_range() {
		req.setSPoints(13);
	}

	@Given("I have story points with two as value")
	public void i_have_story_points_with_two_as_value() {
		req.setSPoints(2);
	}

	@Given("I have story points with eight as value")
	public void i_have_story_points_with_eight_as_value() {
		req.setSPoints(8);
	}
		
	@Given("i prepare the resource")
	public void i_prepare_the_resource() {
		base.requestBody = req.requestBody();
	}

	@When("I create a new story on the api")
	public void i_create_a_new_story_on_the_api() {
		base.apiResource = ApiPaths.RESOURCES_API_JAVA;
		base.ServiceApi = new ApiTools();
		base.response = base.ServiceApi.POSTMethod(base.apiResource, base.requestBody);
	}

	@Then("The API should indicate that the story was created with a {int} status code")
	public void the_API_should_indicate_that_the_story_was_created_with_a_status_code(int statusCode) {
		int result = Integer.parseInt(base.response.getStatusCode().toString().substring(0, 3));
		assertEquals(statusCode, result);
	}
	
	@Then("The API should indicate that the story was not created with a {int} status code")
	public void the_API_should_indicate_that_the_story_was_not_created_with_a_status_code(int statusCode) {
		int result = Integer.parseInt(base.response.getStatusCode().toString().substring(0, 3));
		assertEquals(statusCode, result);
	}
	
	@Then("The error message should have the correct string")
	public void the_error_message_should_have_the_correct_string() {
		base.responseBody = base.response.getBody();
		JSONObject result = new JSONObject(base.responseBody);
		String message = "", priority = req.getPriority(), description=req.getDescription();
		int sP= req.getSPoints();
		
		if(priority!="High"&&priority!="Medium"&&priority!="Low") {
			message = result.getString("message");
			assertEquals("The priority field only accepts 3 values {High, Medium, Low}", message);
		}else if(description==""){
			message = result.getString("message");
			assertEquals("The description field value should not be null or empty", message);
		}else if(sP!=1&&sP!=2&&sP!=3&&sP!=5&&sP!=8) {
			message = result.getString("message");
			assertEquals("The storyPoints field only accepts the following values {1, 2, 3, 5, 8}", message);
		}
	}

	@Then("The API should return a random id")
	public void the_API_should_return_a_random_id() {
		base.responseBody = base.response.getBody();
		JSONObject result = new JSONObject(base.responseBody);
		result.has("id");
	}

	@Then("I verify in the database if the story exist")
	public void i_verify_in_the_database_if_the_story_exist() {
		JSONObject result = new JSONObject(base.responseBody);
		assert true == MongoDBUtils.existID("TEST", "at-resources-db", "stories", result.getString("id"));
	}
}
