package org.restaurant.restaurantsiege_api.model;

import lombok.*;

import java.util.List;

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
    private OrderStatus status;
}
