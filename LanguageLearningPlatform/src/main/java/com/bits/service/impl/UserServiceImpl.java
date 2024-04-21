package com.bits.service.impl;

import com.bits.dto.UserLoginDto;
import com.bits.dto.UserRegistrationDto;
import com.bits.model.User;
import com.bits.repository.UserRepository;
import com.bits.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public static final String SECRET_KEY = "LanguageLearningPlatform";

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        userRepository.save(user);
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {

        List<User> users = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
               if (!CollectionUtils.isEmpty(users)) {
            return generateToken(users.get(0));
        } else {
            return null;
        }
    }

    private String generateToken(User user) {

        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, SECRET_KEY).
                setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp + 10 * 60 * 1000))
                .claim("userId", user.getId()).claim("emailId", user.getEmail()).compact();
        return token;
    }
}
