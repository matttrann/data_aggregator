import React, { useState, useEffect } from 'react';
import { Row, Col, Alert } from 'react-bootstrap';
import SearchForm from '../components/SearchForm';
import ResultsTable from '../components/ResultsTable';
import { performSearch, getUserSearchHistory } from '../services/api';

const Dashboard = () => {
  const [searchResults, setSearchResults] = useState([]);
  const [error, setError] = useState('');
  const [searchHistory, setSearchHistory] = useState([]);

  useEffect(() => {
    // Load search history when component mounts
    const loadSearchHistory = async () => {
      try {
        const history = await getUserSearchHistory();
        setSearchHistory(history);
      } catch (err) {
        console.error('Error loading search history', err);
        setError('Failed to load search history');
      }
    };

    loadSearchHistory();
  }, []);

  const handleSearch = async (query, type) => {
    try {
      setError('');
      const result = await performSearch(query, type);
      setSearchResults(result.results || []);
      
      // Update search history
      setSearchHistory([result, ...searchHistory]);
    } catch (err) {
      console.error('Error performing search', err);
      setError('An error occurred while searching. Please try again.');
    }
  };

  return (
    <div>
      <h1 className="mb-4">Data Aggregator Dashboard</h1>
      
      {error && (
        <Alert variant="danger" className="mb-4">
          {error}
        </Alert>
      )}
      
      <Row>
        <Col md={4}>
          <SearchForm onSearch={handleSearch} />
        </Col>
        <Col md={8}>
          <ResultsTable results={searchResults} />
        </Col>
      </Row>
    </div>
  );
};

export default Dashboard; 