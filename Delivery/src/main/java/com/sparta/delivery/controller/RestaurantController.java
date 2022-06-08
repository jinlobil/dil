package com.sparta.delivery.controller;


import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.repository.RestaurantRepository;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    // 서비스로 생성 보냄
    @PostMapping("/api/restaurant/register")
    public Restaurant createContents(@RequestBody RestaurantRequestDto requestDto) {
        Restaurant restaurant = new Restaurant(requestDto);
        return restaurantService.create(restaurant);
    }
    // 전체 조회 레포로 보냄
    @GetMapping("/api/restaurant")
    public List<Restaurant> restaurantList() {
        return restaurantRepository.findAll();
    }

}
