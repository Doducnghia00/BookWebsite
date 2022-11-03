package com.example.SpringWebsite.config;

import com.example.SpringWebsite.model.Account;
import com.example.SpringWebsite.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Configuration
public class Config  implements WebMvcConfigurer {




    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path bookCoverDir = Paths.get("./BookCover");
        String coverPath = bookCoverDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/BookCover/**").addResourceLocations("file:/" + coverPath + "/");
    }
}
