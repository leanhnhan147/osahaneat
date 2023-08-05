package com.cybersoft.osahaneat.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String fullname;
    private String email;
    private String password;
    private Long roleId;
}
