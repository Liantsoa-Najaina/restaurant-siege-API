package org.restaurant.restaurantsiege_api.model;

import java.util.List;

public class DishOrder {
    private Long id;
    private Dish dish;
    private Integer quantity;
    private List<DOStatus> statusList;
    private OrderStatus status;
}
