package com.bits.repository;

import com.bits.model.User;
import com.bits.model.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
    List<UserLanguage> findByUserId(Long userId);
    Optional<UserLanguage> findByUserIdAndLanguageId(Long userId, Long languageId);
    void deleteByUser(User user);
}
