package com.example.SpringWebsite.model;

import javax.persistence.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private String payMethod;
    private String deliveryAddress;
    private double totalAmount;

    private int status;

    /*
    Status code:
        -1: Cancelled
         0: In cart
         1: Pending
         2: To ship
         3: Completed

    * */


    public Purchase() {
    }

    public Purchase(Integer id, Account account, String payMethod, String deliveryAddress, double totalAmount, int status) {
        this.id = id;
        this.account = account;
        this.payMethod = payMethod;
        this.deliveryAddress = deliveryAddress;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", account=" + account +
                ", payMethod='" + payMethod + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}
