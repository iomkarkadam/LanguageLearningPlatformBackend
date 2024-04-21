package com.bits.service;

import com.bits.dto.UserLoginDto;
import com.bits.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     void registerUser(UserRegistrationDto userRegistrationDto);

     String loginUser(UserLoginDto userLoginDto);
}
