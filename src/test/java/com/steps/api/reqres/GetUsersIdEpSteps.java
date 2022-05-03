package com.steps.api.reqres;

import com.api.reqres.client.GetUsersIdEpClient;
import com.api.reqres.model.getusersidep.CheckListForGetUsersIdEpResponse;
import com.api.reqres.model.getusersidep.GetUsersIdRequest;
import com.api.reqres.model.getusersidep.GetUsersIdResponse;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.http.HttpStatus;

public class GetUsersIdEpSteps {

    private final GetUsersIdEpClient getUsersIdEpClient = new GetUsersIdEpClient();
    private boolean isSuccessCase;
    private GetUsersIdRequest request;
    private GetUsersIdResponse response;

    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("Send request to GetUsersId ep with {string} for {string}")
    public void sendRequestToGetUsersIdEpWith(String id, String isSuccess) {
        isSuccessCase = Boolean.parseBoolean(isSuccess);
        request = getUsersIdEpClient.prepareId(id);
        if (isSuccessCase) {
            response = getUsersIdEpClient.sendRequest(request, HttpStatus.SC_OK);
        } else {
            response = getUsersIdEpClient.sendRequest(request, HttpStatus.SC_NOT_FOUND);
        }
        scenario.log(String.format("id: %s değeri için dönen HttpStatusCode beklenilen değere eşit", request.getId()));
    }

    @Then("Check GetUsersIdEp response")
    public void checkGetUsersIdEpResponse() {
        CheckListForGetUsersIdEpResponse checkList = CheckListForGetUsersIdEpResponse.builder()
                .isSuccessCase(isSuccessCase)
                .id(request.getId())
                .build();

        getUsersIdEpClient.checkResponse(response, checkList);
        scenario.log(String.format("id: %s değeri için dönen response beklenilen değere eşit", request.getId()));

    }
}
