#Author:  Shilpashree

Feature: Update Batch by Batch ID


    Scenario Outline: Update Batch by Batch Id
      Given Batch exists with name "<batch_name>", description "<batch_description>", status "<batch_status>" and batchNoOfClasses "<batchNoOfClasses>" 
      When The user makes a PUT request for the batch to update "<batch_description>"  and "<batch_status>"
      Then Validate the status code and the updated batch details as "<batch_description>" and "<batch_status>"
      Examples: 
      | batch_name  | batch_description | batch_status  | batchNoOfClasses |
      | Jan23-CRUD-SDET-123 | team20-UpdateBatch | Active | 11 |
  