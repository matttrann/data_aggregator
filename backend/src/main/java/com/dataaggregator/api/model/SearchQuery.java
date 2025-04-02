package com.dataaggregator.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "search_queries")
public class SearchQuery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String queryString;
    
    @Column(nullable = false)
    private String queryType; // email, username, password, etc.
    
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "searchQuery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SearchResult> results = new ArrayList<>();

    // Constructors
    public SearchQuery() {
    }

    public SearchQuery(Long id, String queryString, String queryType, LocalDateTime createdAt, User user, List<SearchResult> results) {
        this.id = id;
        this.queryString = queryString;
        this.queryType = queryType;
        this.createdAt = createdAt;
        this.user = user;
        this.results = results;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
} 