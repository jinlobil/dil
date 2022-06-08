package com.sparta.delivery.service;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    public Food create(Long restaurantId, FoodRequestDto requestDto) {
        Long price = requestDto.getPrice();
        if (price < 0 || price > 1000000) {
            throw new IllegalArgumentException("가격은 0에서 100만원 사이로 입력해주세요");
        }
        if (price %100!=0) {
            throw new IllegalArgumentException("100원 단위로 입력해주세요");
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("식당이 존재하지 않습니다."));
        System.out.println("restaurant = " + restaurant);
        Food food = new Food(requestDto, restaurant);
        System.out.println("food = " + food);
        return foodRepository.save(food);
    }

    public List<Food> getFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

}

