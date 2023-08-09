package com.cybersoft.osahaneat.service.impl;

import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.repository.RestaurantRepository;
import com.cybersoft.osahaneat.service.FileService;
import com.cybersoft.osahaneat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
