package org.restaurant.restaurantsiege_api.model;

import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
public class BestSales {
    private Long id;
    private Instant updatedAt;
    private List<Sale> topSales;
}
