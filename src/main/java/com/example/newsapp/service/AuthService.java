package com.example.newsapp.service;

import com.example.newsapp.model.AppUser;
import com.example.newsapp.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AppUserRepo repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public AppUser register(String username, String password) throws Exception {
        if(repo.findByUsername(username).isPresent()) {
            throw new Exception("Username already exists");
        }
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        return repo.save(user);
    }
}
