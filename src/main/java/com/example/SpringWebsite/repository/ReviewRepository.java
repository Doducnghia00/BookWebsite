package com.example.SpringWebsite.repository;

import com.example.SpringWebsite.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
