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
    public void i_have_a_ID_for_update() {
        request.setId(MongoDBUtils.getRandomID("TEST", "at-resources-db", "stories"));
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

    @Given("I have a acceptanceCriteria to update")
    public void i_have_a_acceptanceCriteria_to_update() { request.setAcceptanceCriteria( val.randomString()); }

    @Given("I have a storyPoints to update")
    public void i_have_a_storyPoints_to_update() {
        request.setStoryPoints(val.randomStoryPoints());
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
}
