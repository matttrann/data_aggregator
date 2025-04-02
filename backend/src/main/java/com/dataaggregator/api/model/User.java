package com.dataaggregator.api.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SearchQuery> searchQueries = new HashSet<>();

    // Constructors
    public User() {
    }

    public User(Long id, String username, String password, String email, Set<SearchQuery> searchQueries) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.searchQueries = searchQueries;
    }

    // Getters and Setters
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

    public Set<SearchQuery> getSearchQueries() {
        return searchQueries;
    }

    public void setSearchQueries(Set<SearchQuery> searchQueries) {
        this.searchQueries = searchQueries;
    }
} 