package org.restaurant.restaurantsiege_api.repository.operations;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.Sale;
import org.restaurant.restaurantsiege_api.repository.DataSource;
import org.restaurant.restaurantsiege_api.repository.mapper.SaleMapper;
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
public class SalesRepository {
    private  final DataSource dataSource;
    private final SaleMapper saleMapper;

    public List<Sale> getAllSales () {
        List<Sale> foundSales = new ArrayList<>();
        String sql = """
                SELECT s.id, s.sales_point_id, s.dish_id, s.quantity_sold, s.total_amount
                FROM sale s
                JOIN sales_point sp ON s.sales_point_id = sp.id
                JOIN dish d ON s.dish_id = d.id
                ORDER BY s.quantity_sold DESC, s.total_amount
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    foundSales.add(saleMapper.apply(resultSet));
                }
            }
            return foundSales;
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
