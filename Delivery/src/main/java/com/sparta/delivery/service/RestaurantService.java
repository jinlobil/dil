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
    public Restaurant create(RestaurantRequestDto requestDto) {
        Restaurant restaurant = new Restaurant(requestDto);
        Long price = requestDto.getMinOrderPrice();
        Long fee = requestDto.getDeliveryFee();
        if (price < 1000 || price > 100000) {
            throw new IllegalArgumentException("가격은 1000원에서 10만원 사이로 입력해주세요");
        }
        if (price %100!=0) {
            throw new IllegalArgumentException("100원 단위로 입력해주세요");
        }
        if (fee < 0 || fee > 10000) {
            throw new IllegalArgumentException("배달금액은 0원에서 1만원 사이로 입력해주세요");
        }
        if (fee %500!=0) {
            throw new IllegalArgumentException("500원 단위로 입력해주세요");
        }

        return restaurantRepository.save(restaurant);

    }
}
