package com.cybersoft.osahaneat.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {
    boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeship,
                             String address, String openDate);
}
