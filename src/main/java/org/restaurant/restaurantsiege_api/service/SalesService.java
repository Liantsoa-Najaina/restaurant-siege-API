package org.restaurant.restaurantsiege_api.service;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.Sale;
import org.restaurant.restaurantsiege_api.repository.operations.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;

    public List<Sale> getAllSales(Integer top) {
        return salesRepository.getAllSales().stream()
                .limit(top)
                .toList();
    }
}
