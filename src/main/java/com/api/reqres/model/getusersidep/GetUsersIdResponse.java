package com.api.reqres.model.getusersidep;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class GetUsersIdResponse {

    private Data data;

    private Support support;

    @Getter
    public static class Data {

        private int id;

        private String email;

        @SerializedName("first_name")
        private String firstName;

        @SerializedName("last_name")
        private String lastName;

        private String avatar;
    }

    @Getter
    public static class Support {

        private String url;

        private String text;
    }
}
