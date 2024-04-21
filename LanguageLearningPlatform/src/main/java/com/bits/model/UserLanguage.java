package com.bits.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_languages")
@Data
public class UserLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(nullable = false)
    private int proficiencyLevel;


    // Getters and setters
}
