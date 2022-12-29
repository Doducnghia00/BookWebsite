package com.example.SpringWebsite.repository;

import com.example.SpringWebsite.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}
