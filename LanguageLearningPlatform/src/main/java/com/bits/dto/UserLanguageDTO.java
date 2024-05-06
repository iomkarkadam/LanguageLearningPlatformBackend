package com.bits.dto;


public class UserLanguageDTO {
    private String userEmail;
    private long languageId;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "UserLanguageDTO{" +
                "userEmail='" + userEmail + '\'' +
                ", languageId='" + languageId + '\'' +
                '}';
    }
}
