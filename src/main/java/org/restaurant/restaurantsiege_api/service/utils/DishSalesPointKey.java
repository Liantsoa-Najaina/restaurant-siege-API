package org.restaurant.restaurantsiege_api.service.utils;

import org.restaurant.restaurantsiege_api.model.Dish;
import org.restaurant.restaurantsiege_api.model.SalesPoint;

public record DishSalesPointKey (Dish dish, SalesPoint salesPoint) {}