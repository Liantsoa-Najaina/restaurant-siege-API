package org.restaurant.restaurantsiege_api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.restaurant.restaurantsiege_api.controller.mapper.DurationUnit;

@AllArgsConstructor
@Getter
public class DishProcessingTimeRest {
    private String salesPoint;
    private String dish;
    private Double preparationDuration;
    private DurationUnit durationUnit;
}
