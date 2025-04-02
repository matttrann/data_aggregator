import React, { useState } from 'react';
import { Table, Card, Badge, Button, Modal } from 'react-bootstrap';

const ResultsTable = ({ results = [] }) => {
  const [selectedResult, setSelectedResult] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const handleClose = () => setShowModal(false);
  
  const handleShowDetails = (result) => {
    setSelectedResult(result);
    setShowModal(true);
  };

  // Parse the JSON data for the selected result
  const getResultDetails = () => {
    if (!selectedResult) return {};
    
    try {
      return JSON.parse(selectedResult.resultData);
    } catch (error) {
      console.error('Error parsing result data', error);
      return {};
    }
  };

  if (results.length === 0) {
    return (
      <Card>
        <Card.Body className="text-center">
          <p className="mb-0">No results found. Try a different search query.</p>
        </Card.Body>
      </Card>
    );
  }

  return (
    <>
      <Card>
        <Card.Header>
          <h4>Search Results</h4>
        </Card.Header>
        <Card.Body>
          <Table striped bordered hover responsive>
            <thead>
              <tr>
                <th>Source</th>
                <th>Found At</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {results.map((result) => (
                <tr key={result.id}>
                  <td>
                    <Badge bg="primary">{result.source}</Badge>
                  </td>
                  <td>{new Date(result.foundAt).toLocaleString()}</td>
                  <td>
                    <Button 
                      size="sm" 
                      variant="info"
                      onClick={() => handleShowDetails(result)}
                    >
                      View Details
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Card.Body>
      </Card>

      <Modal show={showModal} onHide={handleClose} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Leak Details - {selectedResult?.source}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {selectedResult && (
            <pre className="json-output">
              {JSON.stringify(getResultDetails(), null, 2)}
            </pre>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default ResultsTable; 