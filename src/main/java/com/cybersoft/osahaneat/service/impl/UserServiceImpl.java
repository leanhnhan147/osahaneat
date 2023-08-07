package com.cybersoft.osahaneat.service.impl;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Role;
import com.cybersoft.osahaneat.entity.User;
import com.cybersoft.osahaneat.payload.request.SignupRequest;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user: users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullname(user.getFullname());

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

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

    @Override
    public boolean checkLogin(String username, String password) {
        User users = userRepository.findByUsername(username);

        return passwordEncoder.matches(password, users.getPassword());
    }
}
