package com.example.SpringWebsite.config;

import com.example.SpringWebsite.model.Account;
import com.example.SpringWebsite.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

//@Configuration
public class Config {
//    @Bean
//    public AccountRepository getAccRepo(){
//        AccountRepository accountRepository = new AccountRepository() {
//            @Override
//            public Account findById(long id) {
//                return null;
//            }
//
//            @Override
//            public <S extends Account> S save(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public Optional<Account> findById(Long aLong) {
//                return Optional.empty();
//            }
//
//            @Override
//            public boolean existsById(Long aLong) {
//                return false;
//            }
//
//            @Override
//            public Iterable<Account> findAll() {
//                return null;
//            }
//
//            @Override
//            public Iterable<Account> findAllById(Iterable<Long> longs) {
//                return null;
//            }
//
//            @Override
//            public long count() {
//                return 0;
//            }
//
//            @Override
//            public void deleteById(Long aLong) {
//
//            }
//
//            @Override
//            public void delete(Account entity) {
//
//            }
//
//            @Override
//            public void deleteAllById(Iterable<? extends Long> longs) {
//
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends Account> entities) {
//
//            }
//
//            @Override
//            public void deleteAll() {
//
//            }
//        };
//        return accountRepository;
//    }
}
