package com.example.repetitivecrud.dto;

// 제네릭 클래스
public class ApiResponse<T> {

    // 속
    private String message;

    private Integer status;

    private T data;

    // 생
    public ApiResponse(String message, Integer status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public T data() {
        return data;
    }
}
