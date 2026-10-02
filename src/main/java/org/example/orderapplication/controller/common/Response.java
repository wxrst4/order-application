package org.example.orderapplication.controller.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class Response<T> {
    private T data;
    private String message;
    private int statusCode;

    public static <T> Response<T> of(T data) {
        return new Response<>(data, "OK", HttpStatus.OK.value());
    }

    public static <T> Response<T> createdOf(T data) {
        return new Response<>(data, "CREATED", HttpStatus.CREATED.value());
    }

    public static <T> Response<T> noContent() {
        return new Response<>(null, "NO_CONTENT", HttpStatus.NO_CONTENT.value());
    }
}
