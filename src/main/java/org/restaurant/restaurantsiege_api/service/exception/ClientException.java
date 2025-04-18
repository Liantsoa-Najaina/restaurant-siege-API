package org.restaurant.restaurantsiege_api.service.exception;

public class ClientException extends RuntimeException {
    public ClientException(Exception e) {
      super(e);
    }

    public ClientException(String message) {
        super(message);
    }
}
