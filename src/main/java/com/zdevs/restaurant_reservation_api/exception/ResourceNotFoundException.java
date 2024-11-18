package com.zdevs.restaurant_reservation_api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message) {

        super(message);
    }
}
