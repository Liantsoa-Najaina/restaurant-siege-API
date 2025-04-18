package org.restaurant.restaurantsiege_api.model;

import lombok.*;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Data
public class Dish {
    private Long id;
    private String name;
    private Double price;
}
