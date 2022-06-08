package com.sparta.delivery.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.delivery.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Getter
@Entity // 테이블임을 나타냅니다.
public class Restaurant {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String name;

    @Column(nullable = false)
    private Long minOrderPrice;

    @Column(nullable = false)
    private Long deliveryFee;

    @JsonBackReference
    @OneToMany(mappedBy = "restaurant", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();


    public Restaurant(String name, Long minOrderPrice, Long deliveryFee) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }

    public Restaurant(RestaurantRequestDto requestDto) {
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

    public void update(Restaurant restaurant) {
        this.name = restaurant.name;
        this.minOrderPrice = restaurant.minOrderPrice;
        this.deliveryFee = restaurant.deliveryFee;
    }
}