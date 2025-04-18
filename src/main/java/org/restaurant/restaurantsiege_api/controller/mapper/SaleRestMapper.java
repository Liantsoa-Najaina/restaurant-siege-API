package org.restaurant.restaurantsiege_api.controller.mapper;

import org.restaurant.restaurantsiege_api.controller.rest.SaleRest;
import org.restaurant.restaurantsiege_api.model.Sale;
import org.springframework.stereotype.Component;

@Component
public class SaleRestMapper {
    public SaleRest toRest(Sale sale) {
        return new SaleRest(
                sale.getSalesPoint().getName(),
                sale.getDish().getName(),
                sale.getQuantitySold(),
                sale.getTotalAmount()
        );
    }
}
