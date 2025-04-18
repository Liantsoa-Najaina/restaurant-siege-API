package org.restaurant.restaurantsiege_api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaleRest {
    private String salesPoint;
    private String dish;
    private Integer quantitySold;
    private Double totalAmount;
}
