package com.api.reqres.model.postusersep;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckListForPostUsersEpResponse {

    private boolean isSuccessCase;

    private Object expectedName;

    private Object expectedJob;

    private String latestId;
}
