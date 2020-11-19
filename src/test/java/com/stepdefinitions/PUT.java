package com.stepdefinitions;

import com.domain.requestPut;
import com.globalClasses.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.json.JSONObject;

import static org.junit.Assert.assertEquals;


public class PUT {
    requestPut request = new requestPut();

    private BasicSecurityUtil base;
    TestValues val = new TestValues();
    public PUT(BasicSecurityUtil base) {
        this.base = base;
    }

    @Given("I get a story to update")
    public void i_get_a_story_to_update() {
        request.setId(MongoDBUtils.getRandomID("TEST", "at-resources-db", "stories"));
    }

    @Given("I have a lowercase priority to update")
    public void i_have_a_lowercase_priority_to_update() {
        request.setPriority(val.randomPriority().toLowerCase());
    }

    @Given("I have a uppercase priority to update")
    public void i_have_a_uppercase_priority_to_update() {
        request.setPriority(val.randomPriority().toUpperCase());
    }

    @Given("I have a priority to update")
    public void i_have_a_priority_to_update() { request.setPriority(val.randomPriority()); }

    @Given("I have a name to update")
    public void i_have_a_name_to_update() {
        request.setName(val.randomName());
    }

    @Given("I have a description to update")
    public void i_have_a_description_to_update() {
        request.setDescription(val.randomString());
    }

    @Given("I have a description as empty to update")
    public void i_have_a_description_as_empty_to_update() {
        request.setDescription("");
    }

    @Given("I have a acceptanceCriteria to update")
    public void i_have_a_acceptanceCriteria_to_update() { request.setAcceptanceCriteria( val.randomString()); }

    @Given("I have a storyPoints to update")
    public void i_have_a_storyPoints_to_update() {
        request.setStoryPoints(val.randomStoryPoints());
    }

    @Given("I have story points out of accepted range to update")
    public void i_have_story_points_out_of_accepted_range_to_update() {
        request.setStoryPoints(13);
    }

    @Given("I have story points with two as value to update")
    public void i_have_story_points_with_two_as_value_to_update() {
        request.setStoryPoints(2);
    }

    @Given("I have story points with eight as value to update")
    public void i_have_story_points_with_eight_as_value_to_update() {
        request.setStoryPoints(8);
    }

    @Given("I have a progress to update")
    public void i_have_a_progress_to_update() {
        request.setProgress(val.randomProgress());
    }

    @Given("I have a start date as current date to update")
    public void i_have_a_start_date_as_current_date_to_update() { request.setStartDate(val.randomStartDate().toString()); }

    @Given("I have an due date to update")
    public void i_have_an_due_date_to_update() {
        request.setDueDate(val.randomDueDate().toString());
    }

    @Given("I have an create date to update")
    public void i_have_an_create_date_to_update() {
        request.setCreateDate(val.createDate().toString());
    }

    @Given("I have a status to update")
    public void i_have_a_status_to_update() {
        request.setStatus(val.randomStatus());
    }

    @When("I update sprint using PUT operation")
    public void i_update_sprint_using_PUT_operation() {
        JSONObject object = new JSONObject();
        object.put("id", request.getId());
        object.put("priority", request.getPriority() );
        object.put("name", request.getName());
        object.put("description", request.getDescription());
        object.put("acceptanceCriteria", request.getAcceptanceCriteria());
        object.put("storyPoints", request.getStoryPoints());
        object.put("progress", request.getProgress());
        object.put("startDate", request.getStartDate());
        object.put("dueDate", request.getDueDate());
        object.put("createDate", request.getCreateDate());
        object.put("status", request.getStatus());
        base.requestBody = object.toString();

        base.apiResource = ApiPaths.RESOURCES_API_JAVA +"/"+ request.getId();
        base.ServiceApi = new ApiTools();
        base.response = base.ServiceApi.PUTMethod(base.apiResource, base.requestBody);
        base.responseBody = base.response.getBody();
        base.method = "PUT";
    }

    @Then("I should retrieve an OK")
    public void i_should_retrieve_an_OK() {
      String message = base.response.getStatusCode().toString().substring(4);
      assertEquals("OK", message);
    }

    @Then("The API should indicate that the story was not updated with a {int} status code")
    public void the_API_should_indicate_that_the_story_was_not_updated_with_a_status_code(int statusCode) {
        int result = Integer.parseInt(base.response.getStatusCode().toString().substring(0, 3));
        assertEquals(statusCode, result);
    }

    @Then("The error message should give the correct string")
    public void the_error_message_should_give_the_correct_string() {
        base.responseBody = base.response.getBody();
        JSONObject result = new JSONObject(base.responseBody);
        String message = "", priority = request.getPriority(), description=request.getDescription();
        int sP= request.getStoryPoints();

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
}
