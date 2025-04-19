package org.restaurant.restaurantsiege_api.model;

import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class DishOrder {
    private Long id;
    private Dish dish;
    private Integer quantity;
    private List<DOStatus> statusList;
    private OStatusType status;
    private SalesPoint salesPoint;

    public Optional<Duration> getProcessingTimePerUnit() {
        Optional<Instant> inProgressTime = getStatusList().stream()
                .filter(s -> s.getStatus() == DOStatusType.IN_PROGRESS)
                .map(DOStatus::getUpdatedAt)
                .findFirst();

        Optional<Instant> finishedTime = getStatusList().stream()
                .filter(s -> s.getStatus() == DOStatusType.FINISHED)
                .map(DOStatus::getUpdatedAt)
                .findFirst();

        if (inProgressTime.isPresent() && finishedTime.isPresent() && getQuantity() > 0) {
            Duration processingDuration = Duration.between(inProgressTime.get(), finishedTime.get());
            return Optional.of(processingDuration.dividedBy(getQuantity()));
        }

        return Optional.empty();
    }
}
