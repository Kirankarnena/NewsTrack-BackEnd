package com.example.newsapp.repository;

import com.example.newsapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article, Long> {
    // No extra methods needed for now
}
