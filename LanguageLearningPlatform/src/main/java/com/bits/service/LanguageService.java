package com.bits.service;

import com.bits.dto.LanguageDTO;

import java.util.List;

public interface LanguageService {

        List<LanguageDTO> getSupportedLanguages();
        List<LanguageDTO> getLanguagesByUserId(Long userId);
        void updateLanguagesForUser(Long userId, List<Long> languageIds);
        int getProficiencyLevel(Long userId, Long languageId);
        void updateProficiencyLevel(Long userId, Long languageId, int proficiencyLevel);
    }
