package com.example.SpringWebsite.controller;


import com.example.SpringWebsite.model.Account;
import com.example.SpringWebsite.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HomeController {

    final
    AccountRepository accountRepository;

    public HomeController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    @GetMapping("/")
    public String Home(HttpSession session){
        System.out.println("GET HOME");

        return "index";
    }

    @GetMapping("/login")
    public String PageLogin(Model model, HttpSession session){
        System.out.println("GET LOGIN PAGE ");

        model.addAttribute("error","");
        session.removeAttribute("username");
        session.removeAttribute("fullName");


        return "sign-in";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute("account") Account account, Model model, HttpSession session){
        System.out.println("POST LOGIN");

        //Validate field empty
        if( Objects.equals(account.getUsername().trim(), "") || (Objects.equals(account.getPassword(), ""))){
            model.addAttribute("messageUsername",Objects.equals(account.getUsername().trim(), "") ? "This field cannot be empty" : null);
            model.addAttribute("messagePassword", Objects.equals(account.getPassword().trim(), "") ? "This field cannot be empty" : null);
            return "sign-in";
        }





        Account foundAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername().trim(), account.getPassword().trim());
        if(foundAccount != null){
            System.out.println("    Login success: " + foundAccount.getUsername());
            session.setAttribute("username",foundAccount.getUsername());
            session.setAttribute("fullName",foundAccount.getFullName());
            System.out.println("    Fullname:  "+ foundAccount.getFullName());

            return "redirect:/";

        }
        else{

            System.out.println("    login Failure");
            model.addAttribute("error","Wrong username or password! Please try again!");
            //return "redirect:/login";
            return "sign-in";
        }
    }

    @GetMapping("/register")
    public String PageRegister(Model model, HttpSession session){
        System.out.println("GET REGISTER PAGE ");
        return "sign-up";
    }

    @PostMapping("/register")
    public String Register(@ModelAttribute("account") Account account, Model model, HttpSession session){
        System.out.println("POST REGISTER");

        //Validate

        //Validate field empty
        if( Objects.equals(account.getUsername(), "") || (Objects.equals(account.getPassword(), ""))
            || (Objects.equals(account.getNote(), "")) || (Objects.equals(account.getEmail(), ""))
            || (Objects.equals(account.getFullName(), "")) ){
            model.addAttribute("messageUsername",Objects.equals(account.getUsername().trim(), "") ? "This field cannot be empty" : null);
            model.addAttribute("messagePassword", Objects.equals(account.getPassword().trim(), "") ? "This field cannot be empty" : null);
            model.addAttribute("messageRePassword", Objects.equals(account.getNote(), "") ? "This field cannot be empty": null);
            model.addAttribute("messageEmail", Objects.equals(account.getEmail(), "") ? "This field cannot be empty": null);
            model.addAttribute("messageFullName", Objects.equals(account.getFullName(), "") ? "This field cannot be empty": null);
            return "sign-up";
        }else{
            account.setUsername(account.getUsername().trim());
            account.setEmail(account.getEmail().trim());
            account.setFullName(account.getFullName().trim());
        }





        //Validate format
        int error = 0;
        //Validate format username and email
        if(!UsernameIsValid(account.getUsername().trim())){
            model.addAttribute("messageUsername","Usernames can only contain letters (a-z), numbers (0-9),periods (.). The number of characters must be between 5 to 20.");
            error++;
        }
        if (!EmailIsValid(account.getEmail().trim())){
            model.addAttribute("messageEmail","Incorrect email format.");
            error++;
        }


        //Validate password
        if (!PasswordIsValid(account.getPassword())){
            model.addAttribute("messagePassword","Password must not contain spaces and must be 8-30 characters in length");
            error++;
        }

        //Validate re-type password
        if(!Objects.equals(account.getPassword(), account.getNote())){
            model.addAttribute("messageRePassword", "The passwords are not the same.");
            error++;
        }

        //Validate fullname
        if(!FullNameIsValid(account.getFullName().trim())){
            model.addAttribute("messageFullName", "The fullname contains only characters from a to z and is between 2 and 40 characters long");
            error++;
        }

        if (error > 0) return "sign-up";



        //Validate username has been taken or not?
        Account foundAccount = accountRepository.findByUsername(account.getUsername());
        if (foundAccount != null){ //Username already exists in db
            model.addAttribute("messageUsername","Username has taken");
            return "sign-up";
        }

        //Username does not exist in db

        System.out.println("    Register with username = " + account.getUsername());
        System.out.println(account);
        accountRepository.save(account);
        System.out.println("    Account Register : " + account);
        return "redirect:/";


    }



    //Validate username
    public static boolean UsernameIsValid(final String username) {
        String USERNAME_PATTERN = "^[a-z0-9\\\\._-]{5,20}$";

        Pattern pattern = Pattern.compile(USERNAME_PATTERN);

        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    //Validate email
    public static boolean EmailIsValid(final String email) {
        String EMAIL_PATTERN = "^(.+)@(\\S+){5,60}$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Validate password
    public static boolean PasswordIsValid(final String password){
        String PASSWORD_PATTERN = "^[\\S*]{8,30}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    //Validate fullname
    public static boolean FullNameIsValid(final String fullName){

        String FULLNAME_PATTERN = "^[a-zA-Z ]{2,40}+$";
        Pattern pattern = Pattern.compile(FULLNAME_PATTERN);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }
}