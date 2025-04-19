package org.restaurant.restaurantsiege_api.repository.mapper;

import lombok.SneakyThrows;
import org.restaurant.restaurantsiege_api.model.DOStatus;
import org.restaurant.restaurantsiege_api.model.DOStatusType;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.time.Instant;
import java.util.function.Function;

@Component
public class DOStatusMapper implements Function<ResultSet, DOStatus> {
    @SneakyThrows
    @Override
    public DOStatus apply(ResultSet resultSet) {
        DOStatus status = new DOStatus();
        status.setStatus(DOStatusType.valueOf(resultSet.getString("status")));
        status.setUpdatedAt(resultSet.getTimestamp("updatedAt").toInstant());
        return status;
    }
}
