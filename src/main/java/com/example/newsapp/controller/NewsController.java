package com.example.newsapp.controller;

import com.example.newsapp.dto.ArticleDto;
import com.example.newsapp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<ArticleDto> getTopNews() {
        return newsService.getTopNews();
    }
}
