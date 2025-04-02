package com.dataaggregator.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_results")
public class SearchResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String source; // Name of the database/breach source
    
    @Column(nullable = false)
    private LocalDateTime foundAt = LocalDateTime.now();
    
    @Column(columnDefinition = "TEXT")
    private String resultData; // JSON data containing the search result
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_query_id")
    private SearchQuery searchQuery;

    // Constructors
    public SearchResult() {
    }

    public SearchResult(Long id, String source, LocalDateTime foundAt, String resultData, SearchQuery searchQuery) {
        this.id = id;
        this.source = source;
        this.foundAt = foundAt;
        this.resultData = resultData;
        this.searchQuery = searchQuery;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getFoundAt() {
        return foundAt;
    }

    public void setFoundAt(LocalDateTime foundAt) {
        this.foundAt = foundAt;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public SearchQuery getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }
} 