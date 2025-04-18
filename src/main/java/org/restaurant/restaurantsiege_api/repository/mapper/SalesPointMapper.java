package org.restaurant.restaurantsiege_api.repository.mapper;

import lombok.SneakyThrows;
import org.restaurant.restaurantsiege_api.model.SalesPoint;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
public class SalesPointMapper implements Function<ResultSet, SalesPoint> {
    @SneakyThrows
    @Override
    public SalesPoint apply(ResultSet resultSet) {
        return new SalesPoint(
                resultSet.getLong("id"),
                resultSet.getString("name")
        );
    }
}
