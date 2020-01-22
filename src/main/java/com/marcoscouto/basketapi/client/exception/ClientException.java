package com.marcoscouto.basketapi.client.exception;

import java.io.IOException;

public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }
}
