package com.api.reqres.model.getusersidep;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckListForGetUsersIdEpResponse {

    private String id;

    private boolean isSuccessCase;
}
