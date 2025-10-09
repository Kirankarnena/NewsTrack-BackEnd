package com.example.newsapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser user;

    @Column(length = 1000)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String url;

    @Column(length = 2000)
    private String imageUrl;
}
