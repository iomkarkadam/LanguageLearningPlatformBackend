package com.bits.controller;
import com.bits.dto.RefreshTokenRequest;
import com.bits.dto.ResponseDTO;
import com.bits.dto.UserLoginDto;
import com.bits.dto.UserRegistrationDto;
import com.bits.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors()),HttpStatus.BAD_REQUEST);
        }
        // Delegate registration logic to userService
        userService.registerUser(registrationDto);
        ResponseDTO responseDTO= new ResponseDTO();
        responseDTO.setMessage("successfully registered");
        responseDTO.setStatus("200");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }
        // Delegate login logic to userService
        String token = userService.loginUser(loginDto);
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
    }
}
