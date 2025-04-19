package org.restaurant.restaurantsiege_api.service;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.DishOrder;
import org.restaurant.restaurantsiege_api.repository.operations.DishOrderRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DishOrderService {
    private final DishOrderRepository dishOrderRepository;

    public Map<DishOrder, Duration> getProcessingTimesForADish(Long dishId) {
        List<DishOrder> dishOrders =dishOrderRepository.getAllDishOrdersByDishId(dishId);
        Map<DishOrder, Duration> processingTimes = new HashMap<>();

        for (DishOrder dishOrder : dishOrders) {
            dishOrder.getProcessingTimePerUnit()
                    .ifPresent(processingTimePerUnit -> {
                        processingTimes.put(dishOrder, processingTimePerUnit);
                    });
        }
        return processingTimes;
    }
}
