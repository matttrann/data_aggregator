package com.dataaggregator.api.service;

import com.dataaggregator.api.model.SearchQuery;
import com.dataaggregator.api.model.SearchResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SnusbaseService {

    private static final Logger logger = LoggerFactory.getLogger(SnusbaseService.class);
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${snusbase.api.key}")
    private String apiKey;

    @Value("${snusbase.api.url}")
    private String apiUrl;

    @Autowired
    public SnusbaseService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<SearchResult> search(SearchQuery query) {
        try {
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Auth-Key", apiKey);

            // Prepare request body
            Map<String, String> requestMap = new HashMap<>();
            requestMap.put("type", query.getQueryType());
            requestMap.put("term", query.getQueryString());

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestMap, headers);

            // Make API call
            ResponseEntity<String> response = restTemplate.postForEntity(
                    apiUrl + "/search", request, String.class);

            // Process response
            JsonNode responseNode = objectMapper.readTree(response.getBody());
            List<SearchResult> results = new ArrayList<>();

            if (responseNode.has("results") && responseNode.get("results").isArray()) {
                for (JsonNode resultNode : responseNode.get("results")) {
                    if (resultNode.has("database")) {
                        SearchResult result = new SearchResult();
                        result.setSource(resultNode.get("database").asText());
                        result.setResultData(resultNode.toString());
                        result.setSearchQuery(query);
                        results.add(result);
                    }
                }
            }

            return results;
        } catch (Exception e) {
            logger.error("Error while searching Snusbase", e);
            throw new RuntimeException("Failed to search Snusbase", e);
        }
    }
} 