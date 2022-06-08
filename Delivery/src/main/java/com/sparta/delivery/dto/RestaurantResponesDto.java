package com.sparta.delivery.dto;

import lombok.Getter;

@Getter
public class RestaurantResponesDto {

    private Long id;
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
}
