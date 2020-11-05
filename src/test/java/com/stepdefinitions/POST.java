package com.stepdefinitions;

import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;

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
	String priority, name, description, aCriteria, createDate, startDate, dueDate;
	int sPoints, progress, status;
	JSONObject obj = new JSONObject();
	JSONArray jsonResult = new JSONArray();
	TestValues val = new TestValues();

	public POST(BasicSecurityUtil base) {
		this.base = base;
	}

	@Given("I have a priority asigned")
	public void i_have_priority_asigned() {
		priority = val.randomPriority();
	}
	
	@Given("I have a uppercase priority asigned")
	public void i_have_a_uppercase_priority_asigned() {
		priority = val.randomPriority().toUpperCase();
	}

	@Given("I have a story name")
	public void i_have_a_story_name() {
		name = val.randomName();
	}

	@Given("I have a story description")
	public void i_have_a_story_description() {
		description = val.randomString();
	}

	@Given("I have acceptance criteria")
	public void i_have_acceptance_criteria() {
		aCriteria = val.randomString();
	}

	@Given("I have story points asigned")
	public void i_have_story_points_asigned() {
		sPoints = val.randomStoryPoints();
	}

	@Given("I have a progress")
	public void i_have_a_progress() {
		progress = val.randomProgress();
	}

	@Given("I have the start date")
	public void i_have_the_start_date() {
		startDate = val.randomStartDate().toString();
	}

	@Given("I have the due date")
	public void i_have_the_due_date() {
		dueDate = val.randomDueDate().toString();
	}

	@Given("I have the create date")
	public void i_have_the_create_date() {
		createDate = val.createDate().toString();
	}

	@Given("I have a status")
	public void i_have_a_status() {
		status = val.randomStatus();
	}

	@Given("i prepare the resource")
	public void i_prepare_the_resource() {
		obj.put("priority", priority);
		obj.put("name", name);
		obj.put("description", description);
		obj.put("acceptanceCriteria", aCriteria);
		obj.put("storyPoints", sPoints);
		obj.put("progress", progress);
		obj.put("startDate", startDate);
		obj.put("dueDate", dueDate);
		obj.put("createDate", createDate);
		obj.put("status", status);
		base.requestBody = obj.toString();
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
