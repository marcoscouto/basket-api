package com.marcoscouto.basketapi.model;

import org.springframework.stereotype.Component;

@Component
public class Response {

    private Integer code;
    private String message;

    public Response() {
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
