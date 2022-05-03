package com.base.client;

import com.base.config.AppConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class HttpClient {

    private final AppConfig appConfig;

    public HttpClient(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public Response post(String path, Object body) {

        return given()
                .log().method()
                .log().uri()
                .log().body()
                .baseUri(appConfig.url)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(path)
                .then()
                .log().body()
                .log().status()
                .extract().response();
    }

    public Response get(String path) {

        return given()
                .log().method()
                .log().uri()
                .log().body()
                .baseUri(appConfig.url)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .log().body()
                .log().status()
                .extract().response();
    }

    public Response getWithQueryParams(String path, Map<String, Object> query) {

        return given()
                .log().method()
                .log().uri()
                .baseUri(appConfig.url)
                .contentType(ContentType.JSON)
                .when()
                .queryParams(query)
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }
}
