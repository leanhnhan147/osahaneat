package com.cybersoft.osahaneat.service.impl;

import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.repository.RestaurantRepository;
import com.cybersoft.osahaneat.service.FileService;
import com.cybersoft.osahaneat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileService fileService;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeship,
                                    String address, String openDate) {
        try{
            boolean isSaveFileSuccess = fileService.saveFile(file);
            if(isSaveFileSuccess){
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(isFreeship);
                restaurant.setAddress(address);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date openDate1 =  simpleDateFormat.parse(openDate);
                restaurant.setOpenDate(openDate1);

                restaurantRepository.save(restaurant);
                return true;
            }
        }catch (Exception e){
            System.out.println("Erro insert restaurant: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<RestaurantDTO> getHomeRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageRequest);

        for (Restaurant restaurant: restaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setImage(restaurant.getImage());
            restaurantDTO.setTitle(restaurant.getTitle());
            restaurantDTO.setSubtitle(restaurant.getSubtitle());
            restaurantDTO.setFreeship(restaurant.isFreeship());
            restaurantDTO.setRating(calculatorRating(restaurant.getRatingRestaurants()));

            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    private double calculatorRating(Set<RatingRestaurant> ratingRestaurantSet){
        double totalPoint = 0;
        for (RatingRestaurant ratingRestaurant: ratingRestaurantSet) {
            totalPoint += ratingRestaurant.getRatePoint();
        }
        return totalPoint/ratingRestaurantSet.size();
    }
}
