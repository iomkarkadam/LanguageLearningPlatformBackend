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

    @Autowired
    EmailServiceImpl emailService;

    public static final String SECRET_KEY = "LanguageLearningPlatform";

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        userRepository.save(user);
        sendEmailOnRegistration(userRegistrationDto.getEmail());
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {

        List<User> users = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
        if (!CollectionUtils.isEmpty(users)) {
            sendEmailOnLogin(userLoginDto.getEmail());
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

    private void sendEmailOnRegistration(String userEmail) {
        String body = "Dear user,\n" +
                "\n" +
                "Welcome to our application! We're excited to have you on board.\n" +
                "Thank you for registering with us.\n" +
                "\n" +
                "Best regards,\n" +
                "The LanguageLearningPlatform Application Team";
        emailService.sendEmail(userEmail, "Welcome to language Learning Platform!", body);
    }

    private void sendEmailOnLogin(String userEmail) {
        String body = "Dear Learner,\n" +
                "\n" +
                "You have successfully logged on 'Language Learning Platform' application.\n" +
                "If you suspect any unusual account activity, please contact us immediately.\n" +
                "If you want to report this as fraud, please call 9999999999 or 1111111111 from India or 8888888888 from overseas.\n" +
                "\n" +
                "\n" +
                "Yours sincerely,\n" +
                "Language Learning Platform-India\n" +
                "\n" +
                "(This is a system generated communication and hence please do not reply to this email)";
        emailService.sendEmail(userEmail, "Login Alert-language Learning Platform!", body);
    }
}
