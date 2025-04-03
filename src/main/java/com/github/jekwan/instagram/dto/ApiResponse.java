package com.github.jekwan.instagram.dto;

import java.util.List;

public class ApiResponse<T> {
    private int code;
    private T data;
    private List<ApiError> errors;

    public ApiResponse() {}
    public ApiResponse(int code, T data, List<ApiError> errors) {
        this.code = code;
        this.data = data;
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }
}
