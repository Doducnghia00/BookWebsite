package com.example.SpringWebsite.repository;

import com.example.SpringWebsite.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<BookEntity, Integer>{
    BookEntity findById(int id);
}
