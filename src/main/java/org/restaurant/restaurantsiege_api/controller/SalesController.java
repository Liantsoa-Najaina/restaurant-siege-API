package org.restaurant.restaurantsiege_api.controller;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurantsiege_api.controller.mapper.SaleRestMapper;
import org.restaurant.restaurantsiege_api.controller.rest.BestSalesRest;
import org.restaurant.restaurantsiege_api.controller.rest.SaleRest;
import org.restaurant.restaurantsiege_api.service.SalesService;
import org.restaurant.restaurantsiege_api.service.exception.ClientException;
import org.restaurant.restaurantsiege_api.service.exception.NotFoundException;
import org.restaurant.restaurantsiege_api.service.exception.ServerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    private final SaleRestMapper saleRestMapper;

    @GetMapping("/bestSales")
    public ResponseEntity<Object> getBestSales(@RequestParam (name = "top") Integer top){
        if(top == null){
            return ResponseEntity.badRequest().body("No top value provided");
        }

        try {
            List<SaleRest> saleRests = salesService.getAllSales(top).stream()
                    .map(saleRestMapper::toRest) // .map(sale -> saleRestMapper.toRest(sale)
                    .toList();

            BestSalesRest bestSalesRest = new BestSalesRest(
                    Instant.now(),
                    saleRests
            );
            return ResponseEntity.ok().body(bestSalesRest);
        } catch (ServerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (ClientException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
