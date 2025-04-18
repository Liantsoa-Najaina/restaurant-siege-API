package org.restaurant.restaurantsiege_api.repository.operations;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.SalesPoint;
import org.restaurant.restaurantsiege_api.repository.DataSource;
import org.restaurant.restaurantsiege_api.repository.mapper.SalesPointMapper;
import org.restaurant.restaurantsiege_api.service.exception.NotFoundException;
import org.restaurant.restaurantsiege_api.service.exception.ServerException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class SalesPointRepository {
    private final DataSource dataSource;
    private final SalesPointMapper salesPointMapper;

    public SalesPoint getById(Long idToFind) {
        String sql = """
                SELECT id, name
                FROM sales_point
                WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, idToFind);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return salesPointMapper.apply(resultSet);
                }
                throw new NotFoundException("No sales point with id " + idToFind);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
