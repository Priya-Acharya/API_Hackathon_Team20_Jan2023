

Feature: Updating batch by BatchId
  I want to use this template for my feature file

 
  Scenario: Update batch by BatchId
  	Given User request for a service to update with base URL and request a payload 
		When PUT request is made by the User with an endpoint "/batches/{batchId}"
		Then User get the status code as "HTTP/1.1 201 " and the response body as updated.
		
  
  