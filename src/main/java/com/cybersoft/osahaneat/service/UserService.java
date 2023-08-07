package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.payload.request.SignupRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();
    boolean addUser(SignupRequest signupRequest);

    boolean checkLogin(String username, String password);

}
