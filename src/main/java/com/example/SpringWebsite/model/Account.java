package com.example.SpringWebsite.model;


import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    private String fullName;
    private String dateOfBirth;
    private String note;

    private  int role;

    public Account() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
