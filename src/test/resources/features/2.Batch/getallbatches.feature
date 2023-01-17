#Author:Shilpashree

Feature: Get all batches details.
  I want to use this template for my feature file

  Scenario: Get batch details and do necessary validations
  	Given User request for a service with base URL  along with metadata information in header 
  	When GET request is made by the user with an endpoint "batches"
  	Then User get the status code as "HTTP/1.1 200 " and validate response body
  	