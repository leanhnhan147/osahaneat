package com.cybersoft.osahaneat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private String image;
    private String title;
    private double rating;
    private String subtitle;
    private boolean isFreeship;
}
