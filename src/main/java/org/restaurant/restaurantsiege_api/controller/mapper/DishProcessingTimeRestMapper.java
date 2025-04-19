package org.restaurant.restaurantsiege_api.controller.mapper;

import org.restaurant.restaurantsiege_api.controller.rest.DishProcessingTimeRest;
import org.restaurant.restaurantsiege_api.model.DishOrder;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DishProcessingTimeRestMapper {
    public DishProcessingTimeRest toRest(DishOrder dishOrder, Duration duration, DurationUnit durationUnit) {
        double converted = switch (durationUnit) {
            case MINUTES -> duration.toMillis() / 1000.0 / 60;
            case HOURS -> duration.toMillis() / 1000.0 / 60 / 60;
            default -> duration.toMillis() / 1000.0;
        };
        return new DishProcessingTimeRest(
                dishOrder.getSalesPoint().getName(),
                dishOrder.getDish().getName(),
                converted,
                durationUnit
        );
    }
}
