package org.restaurant.restaurantsiege_api.repository.operations;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.DishOrder;
import org.restaurant.restaurantsiege_api.repository.DataSource;
import org.restaurant.restaurantsiege_api.repository.mapper.DishOrderMapper;
import org.restaurant.restaurantsiege_api.service.exception.ServerException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DishOrderRepository {
    private final DataSource dataSource;
    private final DishOrderMapper dishOrderMapper;

    public List<DishOrder> getAllDishOrdersByDishId(Long dishId) {
        List<DishOrder> dishOrders = new ArrayList<>();
        String sql = """
                SELECT d_o.id, d_o.id_dish, d_o.quantity, d_o.order_status, d_o.sales_point_id
                FROM dish_order d_o
                JOIN dish d ON d_o.id_dish = d.id
                JOIN sales_point sp ON d_o.sales_point_id = sp.id
                WHERE d.id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, dishId);
            try (ResultSet resultSet = statement.executeQuery()) {
                dishOrders.add(dishOrderMapper.apply(resultSet));
            }
            return dishOrders;
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
