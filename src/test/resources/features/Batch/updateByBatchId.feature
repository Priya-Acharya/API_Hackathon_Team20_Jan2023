#Author:  Shilpashree

Feature: Update Batch by Batch ID

   Background: 
    Given  A Program exists
   	
  @tag2
    Scenario Outline: Update Batch by Batch Id
      When The user makes a PUT request for the program to update "<desc>"  and "<batchstatus>"
      Then Validate the status code and the updated program details as "<desc>" and "<batchstatus>"

		 Examples: 
    | desc | batchstatus|
    | updatebatch | InActive |
  