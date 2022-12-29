package com.example.SpringWebsite.model;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column
    private String content;

    @Column
    private Float rating;

    @Column
    private String datePost;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;

    public Review() {
    }

    public Review(Integer reviewId, String content, Float rating, String datePost, BookEntity bookId, Account accountId) {
        this.reviewId = reviewId;
        this.content = content;
        this.rating = rating;
        this.datePost = datePost;
        this.bookId = bookId;
        this.accountId = accountId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public BookEntity getBookId() {
        return bookId;
    }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }
}