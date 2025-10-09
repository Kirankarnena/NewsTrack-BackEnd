package com.example.newsapp.repository;

import com.example.newsapp.model.History;
import com.example.newsapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepo extends JpaRepository<History, Long> {
    List<History> findByUser(AppUser user);
}
