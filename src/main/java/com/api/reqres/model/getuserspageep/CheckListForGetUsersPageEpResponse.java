package com.api.reqres.model.getuserspageep;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckListForGetUsersPageEpResponse {

    private String page;

    private boolean isSuccessCase;
}
