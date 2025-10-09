package com.example.newsapp.service;

import com.example.newsapp.dto.ArticleDto;
import com.example.newsapp.model.Article;
import com.example.newsapp.repository.ArticleRepo;
import org.json.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class NewsService {

    @Value("${newsapi.key}")
    private String apiKey;

    private final String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";

    private final ArticleRepo articleRepo;

    public NewsService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<ArticleDto> getTopNews() {
        RestTemplate rest = new RestTemplate();
        List<ArticleDto> articles = new ArrayList<>();
        try {
            String resp = rest.getForObject(url + apiKey, String.class);
            JSONObject obj = new JSONObject(resp);
            JSONArray arr = obj.getJSONArray("articles");

            // Clear all old articles in the cache
            articleRepo.deleteAllInBatch();

            // Convert JSON articles to entities and save in the database cache
            List<Article> articleEntities = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject a = arr.getJSONObject(i);
                Article article = new Article(
                        null,
                        a.getString("title"),
                        a.optString("description"),
                        a.getString("url"),
                        a.optString("urlToImage")
                );
                articleEntities.add(article);

                // For DTO response
                articles.add(new ArticleDto(
                        article.getTitle(),
                        article.getDescription(),
                        article.getUrl(),
                        article.getImageUrl()
                ));
            }
            articleRepo.saveAll(articleEntities);
            return articles;
        } catch (Exception e) {
            // External API failed, fetch cached articles
            List<Article> cachedArticles = articleRepo.findAll();
            for (Article article : cachedArticles) {
                articles.add(new ArticleDto(
                        article.getTitle(),
                        article.getDescription(),
                        article.getUrl(),
                        article.getImageUrl()
                ));
            }
            return articles;
        }
    }
}
