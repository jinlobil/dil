package com.sparta.delivery.dto;


import lombok.Getter;

@Getter
public class RestaurantRequestDto {
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
}
