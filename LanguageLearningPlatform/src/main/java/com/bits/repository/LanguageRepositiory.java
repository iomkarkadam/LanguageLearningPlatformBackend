package com.bits.repository;

import com.bits.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepositiory  extends JpaRepository<Language,Long> {
}
