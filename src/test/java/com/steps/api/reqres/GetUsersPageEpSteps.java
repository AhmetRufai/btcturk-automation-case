package com.steps.api.reqres;

import com.api.reqres.client.GetUsersPageEpClient;
import com.api.reqres.model.getuserspageep.CheckListForGetUsersPageEpResponse;
import com.api.reqres.model.getuserspageep.GetUsersPageRequest;
import com.api.reqres.model.getuserspageep.GetUsersPageResponse;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.http.HttpStatus;

public class GetUsersPageEpSteps {

    private final GetUsersPageEpClient getUsersPageEpClient = new GetUsersPageEpClient();
    private boolean isSuccessCase;
    private GetUsersPageRequest request;
    private GetUsersPageResponse response;

    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("Send request to GetUsersPage ep with {string} for {string}")
    public void sendRequestToGetUsersPageEpWithFor(String page, String isSuccess) {
        isSuccessCase = Boolean.parseBoolean(isSuccess);
        request = GetUsersPageRequest.builder()
                .page(page)
                .build();

        response = getUsersPageEpClient.sendRequest(request, HttpStatus.SC_OK);
        scenario.log(String.format("page: %s değeri için dönen HttpStatusCode beklenilen değere eşit", request.getPage()));
    }

    @Then("Check GetUsersPageEp response")
    public void checkGetUsersPageEpResponse() {
        CheckListForGetUsersPageEpResponse checkList = CheckListForGetUsersPageEpResponse.builder()
                .isSuccessCase(isSuccessCase)
                .page(request.getPage())
                .build();

        getUsersPageEpClient.checkResponse(response, checkList);
        scenario.log(String.format("page: %s değeri için dönen response beklenilen değere eşit", request.getPage()));
    }
}
