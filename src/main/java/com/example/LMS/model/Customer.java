package com.example.LMS.model;


import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    // For demo: PAN-like unique ID
    @Column(nullable = false, unique = true)
    private String pan;

    public Customer(){}

    public Customer(Long id, String fullName, String email, String phone, String pan) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.pan = pan;
    }

    public Customer(String fullName, String email, String phone, String pan) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.pan = pan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
