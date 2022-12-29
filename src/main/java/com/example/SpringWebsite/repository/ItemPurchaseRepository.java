package com.example.SpringWebsite.repository;

import com.example.SpringWebsite.model.ItemPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPurchaseRepository extends JpaRepository<ItemPurchase, Integer> {

}
