package com.sparta.delivery.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.delivery.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
//2개의 유니크값 칼럼을 조회 비교 하여 중복값을 막음(푸드네임,레스토랑아이디)
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name" , "restaurantId"})})
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    public Food(String name, Long price ){
        this.name = name;
        this.price = price;
    }
    public Food(FoodRequestDto requestDto, Restaurant restaurant) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;

    }
}
