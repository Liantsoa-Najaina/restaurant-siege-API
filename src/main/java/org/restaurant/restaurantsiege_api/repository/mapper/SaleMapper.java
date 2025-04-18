package org.restaurant.restaurantsiege_api.repository.mapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.restaurant.restaurantsiege_api.model.Dish;
import org.restaurant.restaurantsiege_api.model.Sale;
import org.restaurant.restaurantsiege_api.repository.operations.DishRepository;
import org.restaurant.restaurantsiege_api.repository.operations.SalesPointRepository;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SaleMapper implements Function<ResultSet, Sale> {
    private final DishRepository dishRepository;
    private final SalesPointRepository salesPointRepository;

    @SneakyThrows
    @Override
    public Sale apply(ResultSet resultSet) {
        Long salesPointId = resultSet.getLong("sales_point_id");
        Long dishId = resultSet.getLong("dish_id");

        Sale sale = new Sale();
        sale.setId(resultSet.getLong("id"));
        sale.setSalesPoint(salesPointRepository.getById(salesPointId));
        sale.setDish(dishRepository.getById(dishId));
        sale.setQuantitySold(resultSet.getInt("quantity_sold"));
        sale.setTotalAmount(resultSet.getDouble("total_amount"));
        return sale;
    }
}
