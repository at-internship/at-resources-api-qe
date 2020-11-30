package com.stepdefinitions;

import com.domain.requestPostPut;

import com.globalClasses.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class PUT {
    requestPostPut req = new requestPostPut();

    private BasicSecurityUtil base;
    TestValues val = new TestValues();
    public PUT(BasicSecurityUtil base) {
        this.base = base;
    }

    @Given("I get a story to update")
    public void i_get_a_story_to_update() {
        req.setId(MongoDBUtils.getRandomID("TEST", "at-resources-db", "stories"));
    }

    @Given("I create a request to update as follow")
    public void i_create_a_request_to_update_as_follow(Map<String, String> request) {
        if (StringUtils.isNotBlank(request.get("priority"))) {
            if ("random".equalsIgnoreCase(request.get("priority"))) {
                req.setPriority(val.randomPriority());
            } else if ("lowercase".equalsIgnoreCase(request.get("priority"))) {
                req.setPriority(val.randomPriority().toLowerCase());
            } else if("uppercase".equalsIgnoreCase(request.get("priority"))){
                req.setPriority(val.randomPriority().toUpperCase());
            } else {
                req.setPriority(request.get("priority"));
            }
        }

        if (StringUtils.isNotBlank(request.get("name"))) {
            if ("random".equalsIgnoreCase(request.get("name"))) {
                req.setName(val.randomName());
            } else {
                req.setName(request.get("name"));
            }
        }

        if (StringUtils.isNotBlank(request.get("description"))) {
            if ("random".equalsIgnoreCase(request.get("description"))) {
                req.setDescription(val.randomString());
            } else if ("empty".equalsIgnoreCase(request.get("description"))) {
                req.setDescription("");
            } else {
                req.setDescription(request.get("description"));
            }
        }

        if (StringUtils.isNotBlank(request.get("acceptanceCriteria"))) {
            if ("random".equalsIgnoreCase(request.get("acceptanceCriteria"))) {
                req.setAcceptanceCriteria(val.randomString());
            } else {
                req.setAcceptanceCriteria(request.get("acceptanceCriteria"));
            }
        }

        if (StringUtils.isNotBlank(request.get("storyPoints"))) {
            if ("random".equalsIgnoreCase(request.get("storyPoints"))) {
                req.setStoryPoints(val.randomStoryPoints());
            } else if ("fibonacci".equalsIgnoreCase(request.get("storyPoints"))) {
                req.setStoryPoints(13);
            } else if ("two".equalsIgnoreCase(request.get("storyPoints"))) {
                req.setStoryPoints(2);
            } else if ("eight".equalsIgnoreCase(request.get("storyPoints"))) {
                req.setStoryPoints(8);
            } else {
                req.setStoryPoints(1);
            }
        }

        if (StringUtils.isNotBlank(request.get("progress"))) {
            if ("random".equalsIgnoreCase(request.get("progress"))) {
                req.setProgress(val.randomProgress());
            } else {
                req.setProgress(0);
            }
        }

        if (StringUtils.isNotBlank(request.get("startDate"))) {
            if ("random".equalsIgnoreCase(request.get("startDate"))) {
                req.setStartDate(val.randomStartDate().toString());
            } else {
                req.setStartDate(request.get("startDate"));
            }
        }

        if (StringUtils.isNotBlank(request.get("dueDate"))) {
            if ("random".equalsIgnoreCase(request.get("dueDate"))) {
                req.setDueDate(val.randomDueDate().toString());
            } else {
                req.setDueDate(request.get("dueDate"));
            }
        }

        if (StringUtils.isNotBlank(request.get("createDate"))) {
            if ("random".equalsIgnoreCase(request.get("createDate"))) {
                req.setCreateDate(val.createDate().toString());
            } else {
                req.setCreateDate(request.get("createDate"));
            }
        }

        if (StringUtils.isNotBlank(request.get("status"))) {
            if ("random".equalsIgnoreCase(request.get("status"))) {
                req.setStatus(String.valueOf(val.randomStatus()));
            } else if ("letters".equalsIgnoreCase(request.get("status"))) {
                req.setStatus(val.randomName());
            } else if ("alphanumeric".equalsIgnoreCase(request.get("status"))) {
                req.setStatus(val.randomAlphanumeric());
            } else if ("specialCharacters".equalsIgnoreCase(request.get("status"))) {
                req.setStatus(val.randomSpecialCharacters());
            } else if ("negative".equalsIgnoreCase(request.get("status"))) {
                req.setStatus("-1");
            } else if ("greater".equalsIgnoreCase(request.get("status"))) {
                req.setStatus(String.valueOf(val.randomStatus() + 2));
            } else {
                req.setStatus(request.get("status"));
            }
        }
    }

    @When("I update sprint using PUT operation")
    public void i_update_sprint_using_PUT_operation() {
        base.requestBody = req.requestBody();

        base.apiResource = ApiPaths.RESOURCES_API_JAVA +"/"+ req.getId();
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
        String message = "", priority = req.getPriority(), description = req.getDescription(), status = req.getStatus();
        int sP = req.getStoryPoints();

        if (priority != "High" && priority != "Medium" && priority != "Low") {
            message = result.getString("message");
            assertEquals("The priority field only accepts 3 values {High, Medium, Low}", message);
        }

        if (description == "") {
            message = result.getString("message");
            assertEquals("The description field value should not be null or empty", message);
        }

        if (sP != 1 && sP != 2 && sP != 3 && sP != 5 && sP != 8) {
            message = result.getString("message");
            assertEquals("The storyPoints field only accepts the following values {1, 2, 3, 5, 8}", message);
        }

        if (status.matches("-?[0-9]")) {
            if (Integer.parseInt(status) < 0 && Integer.parseInt(status) > 1) {
                message = result.getString("message");
                assertEquals("The status field only accepts values 0 or 1", message);
            }
        } else {
            message = result.getString("message");
            assertEquals("Global error.", message);
        }
    }
}
