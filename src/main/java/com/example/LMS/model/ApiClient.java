package com.example.LMS.model;


import jakarta.persistence.*;

@Entity
@Table(name = "api_clients")
public class ApiClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false, unique = true)
    private String apiKey;

    public ApiClient() {}

    public ApiClient(Long id, String clientName, String apiKey) {
        this.id = id;
        this.clientName = clientName;
        this.apiKey = apiKey;
    }

    public ApiClient(String clientName, String apiKey) {
        this.clientName = clientName;
        this.apiKey = apiKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
