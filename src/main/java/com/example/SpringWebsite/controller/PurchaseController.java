package com.example.SpringWebsite.controller;

import com.example.SpringWebsite.model.*;
import com.example.SpringWebsite.repository.AccountRepository;
import com.example.SpringWebsite.repository.BookRepository;
import com.example.SpringWebsite.repository.ItemPurchaseRepository;
import com.example.SpringWebsite.repository.PurchaseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class PurchaseController {




    final BookRepository bookRepository;
    final AccountRepository accountRepository;
    final PurchaseRepository purchaseRepository;
    final ItemPurchaseRepository itemPurchaseRepository;

    public PurchaseController(BookRepository bookRepository, AccountRepository accountRepository, PurchaseRepository purchaseRepository, ItemPurchaseRepository itemPurchaseRepository) {
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
        this.purchaseRepository = purchaseRepository;
        this.itemPurchaseRepository = itemPurchaseRepository;
    }




    @GetMapping("/checkout")
    public String displayCheckout(HttpSession session, Model model){
        if (session.getAttribute("username") == null){
            return "redirect:/";
        }
        System.out.println(session.getAttribute("myCart"));
        model.addAttribute("test", "Test");
        model.addAttribute("purchase", new Purchase());
        return "checkout";
    }

//    @PostMapping("/checkout")
//    public String checkout(@ModelAttribute Purchase purchase, Model model, HttpSession session){
//        Account account = accountRepository.findByUsername(session.getAttribute("username") + "");
//        purchase.setAccount(account);
//        purchase.setStatus(1);
//        System.out.println(purchase.toString());
//        return "purchase";
//    }

    @PostMapping("/checkout")
    public String test(@RequestBody String s, HttpSession session) throws Exception{
        System.out.println("Ajax "  + s );


        //Split string
        List<Item> list = new ArrayList<>();
        String[] a = s.split(",");
        List<String> strings = Arrays.asList(a);

        //New + save Purchase
        Purchase purchase = new Purchase();
        Account account = accountRepository.findByUsername(session.getAttribute("username") + "");
        purchase.setAccount(account);
        purchase.setPayMethod(strings.get(0));
        purchase.setNote(strings.get(1));
        purchase.setDeliveryAddress(strings.get(2));
        purchase.setTotalAmount(Double.parseDouble(strings.get(3)));
        purchase.setStatus(1);
        Purchase myPurchase = purchaseRepository.save(purchase);
        System.out.println("myPurchase = " + myPurchase.toString());

        //Save itemPurchase

        for (int i = 4; i < strings.size(); i+=5) {

            Item item = new Item();
            item.setId(Integer.parseInt(strings.get(i)));
            item.setUrl(strings.get(i+1));
            item.setName(strings.get(i+2));
            item.setPrice(Double.parseDouble(strings.get(i+3)));
            item.setQuantity(Integer.parseInt(strings.get(i+4)));

            System.out.println(item.toString());
            list.add(item);


            BookEntity book = bookRepository.findById(item.getId());
            ItemPurchase itemPurchase = new ItemPurchase();
            itemPurchase.setBook(book);
            itemPurchase.setPurchase(myPurchase);
            itemPurchase.setPrice(book.getPrice());
            itemPurchase.setQuantity(item.getQuantity());
            itemPurchaseRepository.save(itemPurchase);
        }


//        String decodedJson = java.net.URLDecoder.decode(json, "UTF-8");
//        ObjectMapper jacksonObjectMapper = new ObjectMapper();
//        List<Item> list = jacksonObjectMapper.readValue(
//                decodedJson, new TypeReference<List<Item>>() {}
//        );
//
        return "index";
    }

    @GetMapping("/purchase")
    public String showUserPurchase(HttpSession session, Model model){
        if (session.getAttribute("username") == null){
            return "redirect:/";
        }
        //purchase
        Account account = accountRepository.findByUsername(session.getAttribute("username")+"");
        List<Purchase> purchases = purchaseRepository.findAllByAccount_Id(account.getId());
        Collections.reverse(purchases);
        model.addAttribute("purchases",purchases);
        //books
        List<ItemPurchase> items = new ArrayList<>();
        for (Purchase p : purchases) {
            List<ItemPurchase> list = itemPurchaseRepository.findAllByPurchase(p);
            items.addAll(list);
        }
        for (ItemPurchase item : items) {
            item.setPrice(item.getPrice() * item.getQuantity());
        }
        model.addAttribute("items", items);


        return "purchase";
    }

    @GetMapping("/purchase/cancel/{id}")
    public String cancelOrder(@PathVariable String id, HttpSession session) throws Exception{
        if (session.getAttribute("username") == null){
            return "redirect:/";
        }
        Purchase purchase = purchaseRepository.findById(Integer.parseInt(id));
        purchase.setStatus(-1);
        purchaseRepository.save(purchase);

        return "redirect:/purchase";
    }



    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Integer id, HttpSession session){

        if (session.getAttribute("username") == null){
            return "redirect:/";
        }

        // Is order has purchased by current user?


        return "order-details";
    }









}
