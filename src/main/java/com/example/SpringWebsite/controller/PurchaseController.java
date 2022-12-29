package com.example.SpringWebsite.controller;

import com.example.SpringWebsite.model.Account;
import com.example.SpringWebsite.model.Purchase;
import com.example.SpringWebsite.repository.AccountRepository;
import com.example.SpringWebsite.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PurchaseController {




    final BookRepository bookRepository;
    final AccountRepository accountRepository;

    public PurchaseController(BookRepository bookRepository, AccountRepository accountRepository) {
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
    }




    @GetMapping("/checkout")
    public String displayCheckout(HttpSession session, Model model){
        System.out.println(session.getAttribute("myCart"));
        model.addAttribute("test", "Test");
        model.addAttribute("purchase", new Purchase());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute Purchase purchase, Model model, HttpSession session){
        Account account = accountRepository.findByUsername(session.getAttribute("username") + "");
        purchase.setAccount(account);
        purchase.setStatus(1);
        System.out.println(purchase.toString());
        return "purchase";
    }

    @PostMapping("/add-item")
    public String addItem(){

        return "purchase";
    }





    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Integer id, HttpSession session){

        if (session.getAttribute("username") == null){
            return "redirect:/";
        }

        // Is order has purchased by current user?


        return "order-details";
    }

    @GetMapping("/purchase")
    public String showUserPurchase(){

        return "purchase";
    }

}
