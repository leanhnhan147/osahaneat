package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.payload.request.SignupRequest;

public interface UserService {
    boolean addUser(SignupRequest signupRequest);
}
