package com.api.reqres.client;

import com.api.reqres.model.getusersidep.CheckListForGetUsersIdEpResponse;
import com.api.reqres.model.getusersidep.GetUsersIdRequest;
import com.api.reqres.model.getusersidep.GetUsersIdResponse;
import com.base.client.BaseApiClient;
import com.base.client.HttpClient;
import com.base.config.AppSettings;
import io.restassured.mapper.ObjectMapperType;
import org.testng.asserts.SoftAssert;

import static com.api.reqres.constants.ReqresApiConstants.ReqresApiEPPaths.GET_USERS_ID;

public class GetUsersIdEpClient extends HttpClient implements BaseApiClient<GetUsersIdResponse, GetUsersIdRequest, CheckListForGetUsersIdEpResponse> {

    public GetUsersIdEpClient() {
        super(AppSettings.Instance.reqresApi);
    }

    @Override
    public GetUsersIdResponse sendRequest(GetUsersIdRequest request, int expectedHttpCode) {

        return get(GET_USERS_ID + request.getId())
                .then()
                .statusCode(expectedHttpCode)
                .extract()
                .as(GetUsersIdResponse.class, ObjectMapperType.GSON);
    }

    @Override
    public void checkResponse(GetUsersIdResponse response, CheckListForGetUsersIdEpResponse checkList) {
        SoftAssert softAssert = new SoftAssert();
        if (checkList.isSuccessCase()) {
            softAssert.assertEquals(response.getData().getId(), Integer.parseInt(checkList.getId()), "Response.data.id is null");
            softAssert.assertNotNull(response.getData().getEmail(), "Response.data.email is null");
            softAssert.assertNotNull(response.getData().getFirstName(), "Response.data.first_name is null");
            softAssert.assertNotNull(response.getData().getLastName(), "Response.data.last_name is null");
            softAssert.assertNotNull(response.getData().getAvatar(), "Response.data.avatar is null");
            softAssert.assertNotNull(response.getSupport().getUrl(), "Response.support.url is null");
            softAssert.assertNotNull(response.getSupport().getText(), "Response.support.text is null");
        } else {
            softAssert.assertNull(response.getData(), "Response.data is not null");
            softAssert.assertNull(response.getSupport(), "Response.support is not null");
        }
        softAssert.assertAll();
    }

    public GetUsersIdRequest prepareId(String id) {
        ReqresApiClient reqresApiClient = new ReqresApiClient();
        id = reqresApiClient.isBlankOrNull(id);

        return GetUsersIdRequest.builder()
                .id(id)
                .build();
    }
}
