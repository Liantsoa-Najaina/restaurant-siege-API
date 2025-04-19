package org.restaurant.restaurantsiege_api.repository.operations;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.DOStatus;
import org.restaurant.restaurantsiege_api.repository.DataSource;
import org.restaurant.restaurantsiege_api.repository.mapper.DOStatusMapper;
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
public class DOStatusRepository {
    private final DataSource dataSource;
    private final DOStatusMapper doStatusMapper;

    public List<DOStatus> getDOStatusByDishOrderId(Long dishOrderId) {
        List<DOStatus> doStatusList = new ArrayList<>();

        String sql = """
                SELECT dos.id, dos.dish_order_id, dos.status, dos.updatedat
                FROM dish_order_status dos
                JOIN dish_order d_o ON d_o.id = dos.dish_order_id
                WHERE d_o.id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, dishOrderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    doStatusList.add(doStatusMapper.apply(resultSet));
                }
            }
            return doStatusList;
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
