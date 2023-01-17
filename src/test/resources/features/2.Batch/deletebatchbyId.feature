#Author: Kalaipriya
#Keywords Summary :Delete Batch By Id (Delete any batch) 
Feature: Delete Batch by any batch id

  Scenario: Delete Batch By Id 
    Given A batch with batch ID exists
    When Delete the batch for the given batch Id
    Then The batch should be deleted successfully
  

