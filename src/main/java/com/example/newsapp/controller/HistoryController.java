package com.example.newsapp.controller;

import com.example.newsapp.model.*;
import com.example.newsapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins="*")
public class HistoryController {

    @Autowired private AppUserRepo userRepo;
    @Autowired private HistoryRepo historyRepo;

    @GetMapping
    public List<History> getHistory(Authentication auth){
        AppUser user = userRepo.findByUsername(auth.getName()).orElseThrow();
        return historyRepo.findByUser(user);
    }

    @PostMapping("/add")
    public History addToHistory(Authentication auth, @RequestBody Article article){
        AppUser user = userRepo.findByUsername(auth.getName()).orElseThrow();
        History h = new History();
        h.setUser(user);
        h.setTitle(article.getTitle());
        h.setDescription(article.getDescription());
        h.setUrl(article.getUrl());
        h.setImageUrl(article.getImageUrl());
        return historyRepo.save(h);
    }
}
