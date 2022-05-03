package com.api.reqres.client;

import com.api.reqres.model.postusersep.*;
import com.base.client.BaseApiClient;
import com.base.client.HttpClient;
import com.base.config.AppSettings;
import io.restassured.mapper.ObjectMapperType;
import org.testng.asserts.SoftAssert;

import java.sql.Timestamp;

import static com.api.reqres.constants.ReqresApiConstants.ReqresApiEPPaths.POST_USERS;

public class PostUsersEpClient extends HttpClient implements BaseApiClient<PostUsersEpResponse, PostUsersEpRequest, CheckListForPostUsersEpResponse> {

    public PostUsersEpClient() {
        super(AppSettings.Instance.reqresApi);
    }

    @Override
    public PostUsersEpResponse sendRequest(PostUsersEpRequest request, int expectedHttpStatusCode) {

        return post(POST_USERS, request)
                .then()
                .statusCode(expectedHttpStatusCode)
                .extract()
                .as(PostUsersEpResponse.class, ObjectMapperType.GSON);
    }

    @Override
    public void checkResponse(PostUsersEpResponse response, CheckListForPostUsersEpResponse checkParameterList) {


        SoftAssert softAssert = new SoftAssert();
        if (checkParameterList.isSuccessCase()) {
            softAssert.assertEquals(response.getName(), checkParameterList.getExpectedName(), "ReqresApi.PostUsersEpResponse.name");
            softAssert.assertEquals(response.getJob(), checkParameterList.getExpectedJob(), "ReqresApi.PostUsersEpResponse.job");
            softAssert.assertNotEquals(response.getId(), checkParameterList.getLatestId(), "ReqresApi.PostUsersEpResponse.id");
            checkCreatedAtParameter(response.getCreatedAt());
        } else {
            // Burada fail senaryolar kontrol edilir
            System.out.println();
        }
        softAssert.assertAll();

    }

    private void checkCreatedAtParameter(Timestamp createdAt) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createdAt.toLocalDateTime().toLocalDate(), now.toLocalDateTime().toLocalDate(), "Tarih bilgisi hatalı");
        softAssert.assertEquals(createdAt.toLocalDateTime().getHour(), now.toLocalDateTime().getHour(), "Saat bilgisi hatalı");
        softAssert.assertEquals(createdAt.toLocalDateTime().getMinute(), now.toLocalDateTime().getMinute(), "Dakika bilgisi hatalı");
        softAssert.assertAll();
    }

    public PostUsersEpRequest prepareRequest(String name, String job) {

        ReqresApiClient reqresApiClient = new ReqresApiClient();
        boolean isDoubleForName = reqresApiClient.isDouble(name);
        boolean isDoubleForJob = reqresApiClient.isDouble(job);
        if (isDoubleForName && isDoubleForJob) {
            return PostUsersEpRequest.builder()
                    .name(Double.parseDouble(name))
                    .job(Double.parseDouble(job))
                    .build();
        } else if (isDoubleForName) {
            return PostUsersEpRequest.builder()
                    .name(Double.parseDouble(name))
                    .job(job)
                    .build();
        } else if (isDoubleForJob) {
            return PostUsersEpRequest.builder()
                    .name(name)
                    .job(Double.parseDouble(job))
                    .build();
        } else {
            name = reqresApiClient.isBlankOrNull(name);
            job = reqresApiClient.isBlankOrNull(job);
            return PostUsersEpRequest.builder()
                    .name(name)
                    .job(job)
                    .build();
        }
    }
}
