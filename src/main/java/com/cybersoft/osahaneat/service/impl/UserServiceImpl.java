package com.cybersoft.osahaneat.service.impl;

import com.cybersoft.osahaneat.entity.Role;
import com.cybersoft.osahaneat.entity.User;
import com.cybersoft.osahaneat.payload.request.SignupRequest;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addUser(SignupRequest signupRequest) {
        Role role = new Role();
        role.setId(signupRequest.getRoleId());

        User user = new User();
        user.setFullname(signupRequest.getFullname());
        user.setUsername(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setRole(role);
        try{
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
