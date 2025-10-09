package com.example.newsapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private String title;
    private String description;
    private String url;
    private String imageUrl;
}
