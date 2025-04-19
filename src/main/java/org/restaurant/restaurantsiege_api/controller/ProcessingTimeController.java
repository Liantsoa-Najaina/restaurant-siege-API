package org.restaurant.restaurantsiege_api.controller;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.service.DishOrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProcessingTimeController {
    private final DishOrderService dishOrderService;

    /// rest controller

}
