package com.sparta.delivery.service;

import com.sparta.delivery.domain.Food;
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
    public Food create(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> getFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

}

