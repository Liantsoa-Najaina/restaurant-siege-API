package org.restaurant.restaurantsiege_api.repository.mapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.restaurant.restaurantsiege_api.model.*;
import org.restaurant.restaurantsiege_api.repository.operations.DOStatusRepository;
import org.restaurant.restaurantsiege_api.repository.operations.DishRepository;
import org.restaurant.restaurantsiege_api.repository.operations.SalesPointRepository;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DishOrderMapper implements Function<ResultSet, DishOrder> {
    private final DOStatusRepository doStatusRepository;
    private final DishRepository dishRepository;
    private final SalesPointRepository salesPointRepository;


    @SneakyThrows
    @Override
    public DishOrder apply(ResultSet resultSet) {
        Long dishOrderId = resultSet.getLong("id");
        Long dishId = resultSet.getLong("id_dish");
        Long salesPointId = resultSet.getLong("sales_point_id");

        List<DOStatus> statusList = doStatusRepository.getDOStatusByDishOrderId(dishOrderId);
        Dish orderedDish = dishRepository.getById(dishId);
        SalesPoint salesPoint = salesPointRepository.getById(salesPointId);

        return new DishOrder(
                dishOrderId,
                orderedDish,
                resultSet.getInt("quantity"),
                statusList,
                OStatusType.valueOf(resultSet.getString("order_status")),
                salesPoint
        );
    }
}
