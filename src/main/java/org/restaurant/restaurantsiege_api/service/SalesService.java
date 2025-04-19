package org.restaurant.restaurantsiege_api.service;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.model.Sale;
import org.restaurant.restaurantsiege_api.repository.operations.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;

    public List<Sale> getAllSales(Integer top) {
        Comparator<Sale> bestSalesComparator = Comparator
                .comparingInt(Sale::getQuantitySold).reversed()
                .thenComparing(Comparator.comparingDouble(Sale::getTotalAmount).reversed());

        return salesRepository.getAllSales().stream()
                .sorted(bestSalesComparator)
                .limit(top)
                .toList();
    }
}
