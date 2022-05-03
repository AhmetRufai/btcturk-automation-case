package com.base.client;

public interface BaseApiClient<T, S, U> {

    T sendRequest(S request, int httpCode);

    void checkResponse(T response, U checkList);
}
