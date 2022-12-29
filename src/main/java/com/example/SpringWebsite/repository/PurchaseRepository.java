package com.example.SpringWebsite.repository;

import com.example.SpringWebsite.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByAccount_Id(int id);
    Purchase findById(int id);
}
