package com.bits.repository;

import com.bits.dto.UserLoginDto;
import com.bits.dto.UserRegistrationDto;
import com.bits.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

  public List<User> findByEmailAndPassword(String email, String password);
  User findByEmail(String email);

}
