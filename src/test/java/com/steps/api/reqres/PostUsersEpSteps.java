package com.steps.api.reqres;

import com.api.reqres.client.PostUsersEpClient;
import com.api.reqres.model.postusersep.CheckListForPostUsersEpResponse;
import com.api.reqres.model.postusersep.PostUsersEpResponse;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.api.reqres.model.postusersep.PostUsersEpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

@Slf4j
public class PostUsersEpSteps {

    private final PostUsersEpClient postUsersEpClient = new PostUsersEpClient();
    private PostUsersEpResponse response;
    private PostUsersEpRequest request;

    private static String latestId;
    private boolean isSuccessCase;

    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("Send request to PostUsersEp with {string},{string} for {string}")
    public void sendRequestToPostUserEpWith(String name, String job, String isSuccess) {
        isSuccessCase = Boolean.parseBoolean(isSuccess);
        request = postUsersEpClient.prepareRequest(name, job);

        if (isSuccessCase) {
            response = postUsersEpClient.sendRequest(request, HttpStatus.SC_CREATED);
        } else {
            response = postUsersEpClient.sendRequest(request, HttpStatus.SC_BAD_REQUEST);
        }
        scenario.log(String.format("name: %s ve job: %s değerleri için dönen HttpStatusCode beklenilen değere eşit", request.getName(), request.getJob()));
    }

    @Then("Check PostUsersEp response")
    public void checkResponse() {

        CheckListForPostUsersEpResponse checkParameterList = CheckListForPostUsersEpResponse.builder()
                .isSuccessCase(isSuccessCase)
                .expectedName(request.getName())
                .expectedJob(request.getJob())
                .latestId(latestId)
                .build();

        postUsersEpClient.checkResponse(response, checkParameterList);
        latestId = response.getId();
        scenario.log(String.format("name: %s ve job: %s değerleri için dönen response beklenilen değere eşit", request.getName(), request.getJob()));
    }
}
