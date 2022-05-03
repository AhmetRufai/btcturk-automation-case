package com.api.reqres.model.getuserspageep;

import com.api.reqres.model.getusersidep.GetUsersIdResponse.*;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class GetUsersPageResponse {

    private int page;

    @SerializedName("per_page")
    private int perPage;

    private int total;

    @SerializedName("total_pages")
    private int totalPage;

    private List<Data> data;

    private Support support;
}
