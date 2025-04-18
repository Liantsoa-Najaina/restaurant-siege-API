package org.restaurant.restaurantsiege_api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
public class Sale {
    private Long id;
    private SalesPoint salesPoint;
    private Dish dish;
    private Integer quantitySold;
    private Double totalAmount;
}
