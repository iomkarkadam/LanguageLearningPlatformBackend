package com.bits.service.impl;

import com.bits.dto.LanguageDTO;
import com.bits.model.Language;
import com.bits.model.User;
import com.bits.model.UserLanguage;
import com.bits.repository.LanguageRepositiory;
import com.bits.repository.UserLanguageRepository;
import com.bits.service.LanguageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepositiory languageRepository;

    @Autowired
    private UserLanguageRepository userLanguageRepository;

    @Override
    public List<LanguageDTO> getSupportedLanguages() {
        List<Language> languages = languageRepository.findAll();
        return languages.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<LanguageDTO> getLanguagesByUserId(Long userId) {
        List<UserLanguage> userLanguages = userLanguageRepository.findByUserId(userId);
        return userLanguages.stream().map(UserLanguage::getLanguage).map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void updateLanguagesForUser(Long userId, List<Long> languageIds) {
        User user = new User();
        user.setId(userId);
        userLanguageRepository.deleteByUser(user);
        List<UserLanguage> userLanguages = languageIds.stream()
                .map(languageId -> {
                    UserLanguage userLanguage = new UserLanguage();
                    userLanguage.setUser(user);
                    Language language = new Language();
                    language.setId(languageId);
                    userLanguage.setLanguage(language);
                    return userLanguage;
                })
                .collect(Collectors.toList());
        userLanguageRepository.saveAll(userLanguages);
    }

    @Override
    public int getProficiencyLevel(Long userId, Long languageId) {
        Optional<UserLanguage> userLanguage = userLanguageRepository.findByUserIdAndLanguageId(userId, languageId);
        return userLanguage.map(UserLanguage::getProficiencyLevel).orElse(0);
    }

    @Override
    public void updateProficiencyLevel(Long userId, Long languageId, int proficiencyLevel) {
        Optional<UserLanguage> userLanguageOptional = userLanguageRepository.findByUserIdAndLanguageId(userId, languageId);
        if (userLanguageOptional.isPresent()) {
            UserLanguage userLanguage = userLanguageOptional.get();
            userLanguage.setProficiencyLevel(proficiencyLevel);
            userLanguageRepository.save(userLanguage);
        } else {
            // Handle case when user language entry does not exist
        }
    }

    private LanguageDTO mapToDto(Language language) {
        LanguageDTO dto = new LanguageDTO();
        dto.setId(language.getId());
        dto.setName(language.getName());
        return dto;
    }
}