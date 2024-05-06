package com.bits.controller;

import com.bits.dto.LanguageDTO;
import com.bits.dto.ResponseDTO;
import com.bits.dto.UserLanguageDTO;
import com.bits.model.Language;
import com.bits.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/get-supported-languages")
    public ResponseEntity<List<LanguageDTO>> getSupportedLanguages() {
        List<LanguageDTO> languages = languageService.getSupportedLanguages();
        return ResponseEntity.ok(languages);
    }

    @PostMapping("/update-languages-for-user")
    public ResponseEntity<?> updateLanguagesForUser(@RequestBody UserLanguageDTO userLanguageDTO) {
        languageService.updateLanguagesForUser(userLanguageDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("language updated for user");
        responseDTO.setStatus("200");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get-languages-by-user-id/{userId}")
    public ResponseEntity<List<LanguageDTO>> getLanguagesByUserId(@PathVariable Long userId) {
        List<LanguageDTO> languages = languageService.getLanguagesByUserId(userId);
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/get-proficiency-level/{userId}/proficiency-level/{languageId}")
    public ResponseEntity<Integer> getProficiencyLevel(@PathVariable Long userId, @PathVariable Long languageId) {
        int proficiencyLevel = languageService.getProficiencyLevel(userId, languageId);
        return ResponseEntity.ok(proficiencyLevel);
    }

    @PostMapping("/update-proficiency-level/{userId}/proficiency-level/{languageId}")
    public ResponseEntity<?> updateProficiencyLevel(@PathVariable Long userId, @PathVariable Long languageId, @RequestParam int proficiencyLevel) {
        languageService.updateProficiencyLevel(userId, languageId, proficiencyLevel);
        return ResponseEntity.ok().build();
    }

}
