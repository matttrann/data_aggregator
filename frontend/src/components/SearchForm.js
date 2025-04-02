import React, { useState } from 'react';
import { Form, Button, Card } from 'react-bootstrap';

const SearchForm = ({ onSearch }) => {
  const [query, setQuery] = useState('');
  const [type, setType] = useState('username');
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!query.trim()) return;
    
    setIsLoading(true);
    try {
      await onSearch(query, type);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <Card className="mb-4">
      <Card.Header>
        <h4>Search Data Leaks</h4>
      </Card.Header>
      <Card.Body>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Search Type</Form.Label>
            <Form.Select 
              value={type} 
              onChange={(e) => setType(e.target.value)}
            >
              <option value="username">Username</option>
              <option value="email">Email</option>
              <option value="password">Password (Hash)</option>
              <option value="hash">Hash</option>
              <option value="domain">Domain</option>
              <option value="ip">IP Address</option>
            </Form.Select>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Search Query</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter search term"
              value={query}
              onChange={(e) => setQuery(e.target.value)}
              required
            />
            <Form.Text className="text-muted">
              Enter the {type} you want to search for in data leaks.
            </Form.Text>
          </Form.Group>

          <Button 
            variant="primary" 
            type="submit" 
            disabled={isLoading}
            className="w-100"
          >
            {isLoading ? 'Searching...' : 'Search'}
          </Button>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default SearchForm; 