package org.restaurant.restaurantsiege_api.controller;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.controller.mapper.DishProcessingTimeRestMapper;
import org.restaurant.restaurantsiege_api.controller.mapper.DurationUnit;
import org.restaurant.restaurantsiege_api.controller.rest.BestProcessingTimeRest;
import org.restaurant.restaurantsiege_api.controller.rest.DishProcessingTimeRest;
import org.restaurant.restaurantsiege_api.model.DishOrder;
import org.restaurant.restaurantsiege_api.service.DishOrderService;
import org.restaurant.restaurantsiege_api.service.utils.AggregationType;
import org.restaurant.restaurantsiege_api.service.utils.DishSalesPointKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProcessingTimeController {
    private final DishOrderService dishOrderService;
    private final DishProcessingTimeRestMapper dishProcessingTimeRestMapper;

    @GetMapping
    public BestProcessingTimeRest getBestProcessingTimes(
            @PathVariable("id") Long dishId,
            @RequestParam(name = "top") int top,
            @RequestParam(name = "durationUnit", defaultValue = "SECONDS") DurationUnit durationUnit,
            @RequestParam(name = "calculationMode", defaultValue = "AVERAGE") AggregationType aggregationType
    ) {
        Map<DishSalesPointKey, Duration> durationMap = dishOrderService
                .getProcessingTimeByDishAndSalesPoint(dishId, aggregationType);

        List<DishProcessingTimeRest> bestProcessingTimes = durationMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(top)
                .map(entry -> {
                    DishOrder tempDishOrder = new DishOrder();
                    tempDishOrder.setDish(entry.getKey().dish());
                    tempDishOrder.setSalesPoint(entry.getKey().salesPoint());

                    return dishProcessingTimeRestMapper.toRest(tempDishOrder, entry.getValue(), durationUnit);
                })
                .toList();


        return new BestProcessingTimeRest(Instant.now(), bestProcessingTimes);
    }

    /// rest controller

}
