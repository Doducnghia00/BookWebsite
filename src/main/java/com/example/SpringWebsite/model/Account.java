package com.example.SpringWebsite.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    private String fullName;
    private String dateOfBirth;
    private String note;

    private  int role;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Review> reviewList;

    public Account() {
    }

    public Account(Integer id, String username, String password, String email, String fullName, String dateOfBirth, String note, int role, List<Review> reviewList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.note = note;
        this.role = role;
        this.reviewList = reviewList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public Account(String username, String password, String email, int role) {
        this.username = username;
        this.password = password;
        this.email = email.toLowerCase();
        this.role = role;
    }

    public Account(String username, String password, String email, String fullName, String dateOfBirth, String note, int role) {
        this.username = username;
        this.password = password;
        this.email = email.toLowerCase();
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.note = note;
        this.role = role;
    }
    public Account(String username, String password, String email, String fullName, int role) {
        this.username = username;
        this.password = password;
        this.email = email.toLowerCase();
        this.fullName = fullName;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
