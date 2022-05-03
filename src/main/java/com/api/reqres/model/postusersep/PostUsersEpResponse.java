package com.api.reqres.model.postusersep;

import lombok.Getter;

import java.sql.Timestamp;
@Getter
public class PostUsersEpResponse {
    private Object name;

    private Object job;

    private String id;

    private Timestamp createdAt;
}
