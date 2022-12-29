package com.example.SpringWebsite.model;

import javax.persistence.*;

@Entity
public class ItemPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Purchase purchase;

    private int quantity;
    private double price;

    public ItemPurchase() {
    }

    public ItemPurchase(Integer id, BookEntity book, Purchase purchase, int quantity, double price) {
        this.id = id;
        this.book = book;
        this.purchase = purchase;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Purchase getCart() {
        return purchase;
    }

    public void setCart(Purchase purchase) {
        this.purchase = purchase;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
