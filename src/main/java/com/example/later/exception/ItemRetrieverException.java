package com.example.later.exception;

import java.io.IOException;
import java.net.URISyntaxException;

public class ItemRetrieverException extends RuntimeException {
    public ItemRetrieverException(String message, URISyntaxException e) {
        super(message, e);
    }

    public ItemRetrieverException(String message) {
        super(message);
    }

    public ItemRetrieverException(String message, IOException e) {
        super(message, e);
    }
}
