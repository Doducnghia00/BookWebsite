package com.example.SpringWebsite.controller;

import com.example.SpringWebsite.model.Account;
import com.example.SpringWebsite.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    final
    AccountRepository accountRepository;

    public UserController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/profile")
    public String ShowUserProfile(HttpSession session, Model model){

        if (session.getAttribute("fullName") == null){
            return "redirect:/";
        }else{
            String username  = (String) session.getAttribute("username");
            Account account = accountRepository.findByUsername(username);
            model.addAttribute("account", account);
        }
        return "user-profile";
    }
}
