package com.dataaggregator.api.controller;

import com.dataaggregator.api.model.SearchQuery;
import com.dataaggregator.api.model.User;
import com.dataaggregator.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public ResponseEntity<SearchQuery> search(@RequestBody Map<String, String> searchRequest,
                                             @AuthenticationPrincipal User user) {
        String queryString = searchRequest.get("query");
        String queryType = searchRequest.get("type");
        
        SearchQuery result = searchService.performSearch(queryString, queryType, user);
        return ResponseEntity.ok(result);
    }
} 