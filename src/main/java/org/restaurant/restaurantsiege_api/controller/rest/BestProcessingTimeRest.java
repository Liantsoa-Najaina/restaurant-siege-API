package org.restaurant.restaurantsiege_api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class BestProcessingTimeRest {
    private Instant updatedAt;
    private List<DishProcessingTimeRest> bestProcessingTimes;
}
