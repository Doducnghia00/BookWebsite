package com.example.SpringWebsite.repository;

import com.example.SpringWebsite.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findById(long id);
    Account findByUsername(String username);
    Account findAccountByUsernameAndPassword(String username, String password);
}
