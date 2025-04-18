package org.restaurant.restaurantsiege_api.repository.operations;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.Dish;
import org.restaurant.restaurantsiege_api.repository.DataSource;
import org.restaurant.restaurantsiege_api.repository.mapper.DishMapper;
import org.restaurant.restaurantsiege_api.service.exception.NotFoundException;
import org.restaurant.restaurantsiege_api.service.exception.ServerException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class DishRepository {
    private final DataSource dataSource;
    private final DishMapper dishMapper;

    public Dish getById(Long idToFind) {
        String sql = """
                SELECT id, name, price
                FROM dish
                WHERE id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setLong(1, idToFind);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return dishMapper.apply(resultSet);
                }
                throw new NotFoundException("No such dish with id: " + idToFind);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
