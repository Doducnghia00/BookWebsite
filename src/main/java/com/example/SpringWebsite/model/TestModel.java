package com.example.SpringWebsite.model;

public class TestModel {
    private Account account;
    private Category category;

    public TestModel() {
    }

    public TestModel(Account account, Category category) {
        this.account = account;
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
