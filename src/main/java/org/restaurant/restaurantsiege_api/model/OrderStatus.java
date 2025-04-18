package org.restaurant.restaurantsiege_api.model;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class OrderStatus {
    private OrderStatus status;
    private Instant updatedAt;
}
