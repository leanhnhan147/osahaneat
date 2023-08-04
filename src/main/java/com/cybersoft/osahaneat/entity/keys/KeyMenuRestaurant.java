package com.cybersoft.osahaneat.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class KeyMenuRestaurant implements Serializable {
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "restaurant_id")
    private Long restaurantId;
}
