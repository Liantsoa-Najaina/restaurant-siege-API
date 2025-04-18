package org.restaurant.restaurantsiege_api.model;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
public class SalesPoint {
    private Long id;
    private String name;
    private String url;
    private String apiKey;

    public SalesPoint(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
