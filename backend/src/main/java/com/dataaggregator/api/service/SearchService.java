package com.dataaggregator.api.service;

import com.dataaggregator.api.model.SearchQuery;
import com.dataaggregator.api.model.SearchResult;
import com.dataaggregator.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {

    private final SnusbaseService snusbaseService;

    @Autowired
    public SearchService(SnusbaseService snusbaseService) {
        this.snusbaseService = snusbaseService;
    }
    
    @Transactional
    public SearchQuery performSearch(String queryString, String queryType, User user) {
        // Create and save the search query
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setQueryString(queryString);
        searchQuery.setQueryType(queryType);
        searchQuery.setUser(user);
        
        // Perform the search using Snusbase API
        List<SearchResult> results = snusbaseService.search(searchQuery);
        searchQuery.setResults(results);
        
        return searchQuery;
    }
} 