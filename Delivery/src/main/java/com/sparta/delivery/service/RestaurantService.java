package com.sparta.delivery.service;

import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public Restaurant createRestaurant(RestaurantRequestDto requestDto) {
        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);
        return restaurant;

    };


    public List<Restaurant>getRestaurant() {
       return restaurantRepository.findAll();
    }



    @Transactional // 음식점 수정
    public Long update(Long id, Restaurant restaurant) {
        Restaurant restaurant1 = restaurantRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 음식점이 존재하지 않습니다")
        );
        restaurant.update(restaurant);
        return restaurant1.getId();
    }
    @Transactional
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);

    }
}
