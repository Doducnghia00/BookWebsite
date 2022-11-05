package com.example.SpringWebsite.controller;


import com.example.SpringWebsite.model.BookEntity;
import com.example.SpringWebsite.model.Category;
import com.example.SpringWebsite.repository.AccountRepository;
import com.example.SpringWebsite.repository.BookRepository;
import com.example.SpringWebsite.repository.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class BookController {
    final
    AccountRepository accountRepository;

    final
    BookRepository bookRepository;

    final CategoryRepository categoryRepository;

    public BookController(AccountRepository accountRepository, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/book/{id}")
    public String getBookById(Model model, @PathVariable String id, HttpSession session) {

        if (session.getAttribute("username") == null){
            return "redirect:/";
        }

        List<Category> categoryList  = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("categoryList", categoryList);



        if(id.equals("add")) {
            System.out.println("GET ADD BOOK");
            BookEntity book = new BookEntity();

            model.addAttribute("book", book);
            return "book-info";
        }


        System.out.println("GET BOOK" );
        System.out.println("    ID BOOK = " + id);
        model.addAttribute("id", id);
        BookEntity book = bookRepository.findById(Integer.valueOf(id)).get();
        System.out.println( "   Path:" +book.getBookCoverPath());
        String bookId = book.getId() +"";
        model.addAttribute("idBook", bookId);
        model.addAttribute("book", book);
        return "book-info";
    }


    @PostMapping("/book/{id}")
    public String addBook(@ModelAttribute BookEntity book, Model model, @PathVariable String id, @RequestParam("bookCover") MultipartFile bookCover) throws IOException {
        System.out.println("POST BOOK");

        //Validate field empty
        if(Objects.equals(book.getTitle(), "") || Objects.equals(book.getAuthor(), "")
                || Objects.equals(book.getReleaseDate(), "")) {
            System.out.println("HAVE ERRORS");
//            BookEntity foundBook = bookRepository.findById(book.getId()).get();
//            book.setImage(foundBook.getImage());
//            model.addAttribute("book", book);
            model.addAttribute("messageTitle",Objects.equals(book.getTitle().trim(),"") ? "This field cannot be empty" : null );
            model.addAttribute("messageAuthor",Objects.equals(book.getAuthor().trim(),"") ? "This field cannot be empty" : null );
            model.addAttribute("messageReleaseDate",Objects.equals(book.getReleaseDate().trim(),"") ? "This field cannot be empty" : null );
            if(book.getId()!=null){
                BookEntity foundBook = bookRepository.findById(book.getId()).get();
                book.setImage(foundBook.getImage());
            }
            List<Category> categoryList  = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("book", book);
            return "book-info";
        } else {
            book.setTitle(book.getTitle().trim());
            book.setAuthor(book.getAuthor().trim());
            book.setReleaseDate(book.getReleaseDate().trim());
        }

        // Processing image file
        if(bookCover.isEmpty()){
//            model.addAttribute("book",book);
//            return "book-info";

            //save book
//            id = "add";
//            model.addAttribute("id", id);
            if(book.getId()!=null){
                BookEntity foundBook = bookRepository.findById(book.getId()).get();
                book.setImage(foundBook.getImage());
            }

            System.out.println("    SAVE BOOK "+ book.toString());
            BookEntity currentBook =  bookRepository.save(book);
            return "redirect:/";
        }else{

            //Save image name
            String fileName = StringUtils.cleanPath(bookCover.getOriginalFilename());
            book.setImage(fileName);

            //save book
            id = "add";
            model.addAttribute("id", id);
            System.out.println("    SAVE BOOK "+ book.toString());
            BookEntity currentBook =  bookRepository.save(book);

            //Save image on disk
            String uploadDir = "./BookCover/" +currentBook.getId();

            Path path = Paths.get(uploadDir); //current drive /BookCover or project folder with ./BookCover

            if(!Files.exists(path)){
                Files.createDirectories(path);
            }
            try{
                InputStream inputStream = bookCover.getInputStream();
                Files.copy(inputStream,path.resolve(bookCover.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "redirect:/";
        }





//        return "redirect:/";
    }

    //Not working
    @PutMapping("/book/{id}")
    public String updateBook(@ModelAttribute BookEntity book, Model model, @PathVariable Integer id) {
        System.out.println("PUT BOOK");
        book.setId(id);
        System.out.println("    UPDATE BOOK "+ book.toString());
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }



}
