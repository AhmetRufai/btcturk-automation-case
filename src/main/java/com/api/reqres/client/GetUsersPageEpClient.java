package com.api.reqres.client;

import com.api.reqres.model.getuserspageep.CheckListForGetUsersPageEpResponse;
import com.api.reqres.model.getuserspageep.GetUsersPageRequest;
import com.api.reqres.model.getuserspageep.GetUsersPageResponse;
import com.base.client.BaseApiClient;
import com.base.client.HttpClient;
import com.base.config.AppSettings;
import io.restassured.mapper.ObjectMapperType;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static com.api.reqres.constants.ReqresApiConstants.ReqresApiEPPaths.GET_USERS_ID;

public class GetUsersPageEpClient extends HttpClient implements BaseApiClient<GetUsersPageResponse, GetUsersPageRequest, CheckListForGetUsersPageEpResponse> {

    public GetUsersPageEpClient() {
        super(AppSettings.Instance.reqresApi);
    }

    @Override
    public GetUsersPageResponse sendRequest(GetUsersPageRequest request, int expectedHttpCode) {

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", request.getPage());

        return getWithQueryParams(GET_USERS_ID, queryParams)
                .then()
                .statusCode(expectedHttpCode)
                .extract()
                .as(GetUsersPageResponse.class, ObjectMapperType.GSON);
    }

    @Override
    public void checkResponse(GetUsersPageResponse response, CheckListForGetUsersPageEpResponse checkList) {
        SoftAssert softAssert = new SoftAssert();
        ReqresApiClient reqresApiClient = new ReqresApiClient();

        //This setting was made according to the working structure of EP
        if (checkList.getPage().equals("0") || !reqresApiClient.isInt(checkList.getPage())) {
            checkList.setPage("1");
        }
        softAssert.assertEquals(response.getPage(), Integer.parseInt(checkList.getPage()));

        if (checkList.isSuccessCase()) {
            softAssert.assertTrue(response.getPerPage() >= response.getData().size(),
                    "Response.perPage must be greater than or equal to Response.data.size");
            softAssert.assertTrue(response.getTotal() <= (response.getTotalPage() * response.getPerPage()),
                    "Response.total must be less than or equal to Response.totalPage * Response.perPage");
            softAssert.assertTrue(response.getTotalPage() >= response.getPage(),
                    "Response.totalPage must be greater than or equal to Response.page");
            softAssert.assertTrue(response.getData().size() > 0,
                    "Response.data.size must be greater than 0 for a successful response");
            softAssert.assertNotNull(response.getSupport(),
                    "Response.support must not be null for a successful response");
        } else {
            softAssert.assertTrue(response.getData().size() == 0,
                    "Response.data.size must be 0 for a fail response");
        }
        softAssert.assertAll();
    }
}
