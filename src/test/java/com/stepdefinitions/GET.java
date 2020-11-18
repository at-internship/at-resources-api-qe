package com.stepdefinitions;

import com.globalClasses.ApiPaths;
import com.globalClasses.ApiTools;
import com.globalClasses.BasicSecurityUtil;
import com.globalClasses.MongoDBUtils;
import com.globalClasses.MongoDBConnection;

import com.mongodb.client.MongoDatabase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GET {

    private BasicSecurityUtil base;
    int statusCodeRetrieved;
    JSONArray mongoClean = new JSONArray();
    public GET(BasicSecurityUtil base) {
        this.base = base;
    }

    @Given("I prepare the get operation for resources api")
    public void i_prepare_the_get_operation_for_resources_api() {
        base.param="";
    }

    @When("I call get request to api")
    public void i_call_get_request_to_api() {
        base.apiResource = ApiPaths.RESOURCES_API_JAVA + base.param;
        base.ServiceApi = new ApiTools();
        base.response = base.ServiceApi.retrieve(base.apiResource);
        base.responseBody = base.response.getBody();
        statusCodeRetrieved = base.response.getStatusCodeValue();
    }
    @Then("The status code should be {int}")
    public void the_status_code_should_be(int statusCode) {
        int result = parseInt(base.response.getStatusCode().toString().substring(0, 3));
        assertEquals(statusCode, result);
    }
    @Then("I validate the response for get all stories")
    public void i_validate_the_response_for_get_all_stories() {
        JSONArray mongodb = new JSONArray(MongoDBConnection.convertMongo("TEST","at-resources-db", "stories").toString());
        for(int i = 0; i < mongodb.length(); i++) {
          JSONObject mongoStory = mongodb.getJSONObject(i);
          mongoClean.put(MongoDBUtils.cleanJson(mongoStory));
        }
        JSONAssert.assertEquals(base.responseBody, mongoClean, JSONCompareMode.NON_EXTENSIBLE);
    }
}
