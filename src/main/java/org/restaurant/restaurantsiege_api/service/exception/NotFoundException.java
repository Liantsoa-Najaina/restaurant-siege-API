package org.restaurant.restaurantsiege_api.service.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Exception e) {
      super(e);
    }
    public NotFoundException(String message) {
        super(message);
    }
}
