package com.sparta.delivery.controller;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodService foodService;

    //푸드 생성
    @PostMapping("/api/restaurant/{restaurantId}/food/register")
    public Food createFood(@RequestBody FoodRequestDto requestDto, @PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("식당이 존재하지 않습니다."));
        System.out.println("restaurant = " + restaurant);
        Food food = new Food(requestDto, restaurant);
        System.out.println("food = " + food);
        return foodService.create(food);
    }
    //푸드 전체 조회
    @GetMapping("/api/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable Long restaurantId) {
        return foodService.getFood(restaurantId);
    }
}