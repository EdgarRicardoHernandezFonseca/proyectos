package com.cliente.rest.model;

public class SuccessResponse<T> {

	private int responseStatus;
    private T responseBody;

    public SuccessResponse(int responseStatus, T responseBody) {
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }
}