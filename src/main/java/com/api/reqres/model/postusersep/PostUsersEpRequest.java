package com.api.reqres.model.postusersep;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostUsersEpRequest {

    private Object name;

    private Object job;
}
