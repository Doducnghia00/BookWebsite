package com.example.SpringWebsite.controller;

import com.example.SpringWebsite.model.Account;
import com.example.SpringWebsite.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    final
    AccountRepository accountRepository;

    public UserController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/profile")
    public String ShowUserProfile(HttpSession session, Model model){
        System.out.println("GET PROFILE PAGE");
        if (session.getAttribute("fullName") == null){
            return "redirect:/";
        }else{
            String username  = (String) session.getAttribute("username");
            System.out.println("    "+ username);
            Account account = accountRepository.findByUsername(username);
            model.addAttribute("account", account);

        }
        return "user-profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Account account, HttpSession session, Model model){
        System.out.println("POST PROFILE PAGE");
        String username = (String) session.getAttribute("username");
        Account foundAccount = accountRepository.findByUsername(username);
        //Validate

        //Validate field empty
        if( (Objects.equals(account.getEmail(), ""))
                || (Objects.equals(account.getFullName(), "")) ){
            model.addAttribute("messageEmail", Objects.equals(account.getEmail(), "") ? "This field cannot be empty": null);
            model.addAttribute("messageFullName", Objects.equals(account.getFullName(), "") ? "This field cannot be empty": null);


            model.addAttribute("account", foundAccount);
            return "user-profile";
        }else{
            account.setEmail(account.getEmail().trim());
            account.setFullName(account.getFullName().trim());
        }

        //Validate format
        int errorFormat = 0;
        //Validate format email
        account.setEmail(account.getEmail().toLowerCase());
        if (!EmailIsValid(account.getEmail().trim())){
            model.addAttribute("messageEmail","Incorrect email format.");
            errorFormat++;
        }

        //Validate fullname
        if(!FullNameIsValid(account.getFullName().trim())){
            model.addAttribute("messageFullName", "The fullname contains only characters from a to z and is between 2 and 40 characters long");
            errorFormat++;
        }

        if (errorFormat > 0){
            model.addAttribute("account", foundAccount);
            return "user-profile";
        }

        // Email has taken by other user?
        if (!Objects.equals(account.getEmail(), foundAccount.getEmail())){
            Account existAccount  = accountRepository.findAccountByEmail(account.getEmail());
            if (account.getEmail()!= null){
                model.addAttribute("messageEmail","Email has been taken");
                model.addAttribute("account", foundAccount);
                return "user-profile";
            }
        }



        foundAccount.setFullName(account.getFullName());
        foundAccount.setEmail(account.getEmail());
        foundAccount.setDateOfBirth(account.getDateOfBirth());
        accountRepository.save(foundAccount);

        System.out.println("    Update Information: " + foundAccount.getFullName() + "|" + foundAccount.getEmail() + "|" + foundAccount.getDateOfBirth());

        session.setAttribute("username",foundAccount.getUsername());
        session.setAttribute("fullName",foundAccount.getFullName());

        return "redirect:/profile";
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
