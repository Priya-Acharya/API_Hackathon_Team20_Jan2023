#Author: Gayathri
#Keywords Summary : User can retrieve specific batch based on batch name.Get Batch by using Batch Name
Feature: Get Batch by ProgramId

  @tag1
Scenario Outline: Get Batch by ProgramId
    Given Batch details with name "<batch_name>", description "<batch_description>", status "<batch_status>" and batchNoOfClasses "<batchNoOfClasses>" exists
    When The user makes a GET request for the Batch by ProgramId
    Then The returned Batch result has name as "<batch_name>", description as "<batch_description>" and status as "<batch_status>"

Examples: 
      | batch_name  | batch_description | batch_status  | batchNoOfClasses |
      | Jan23-CRUDInterfacingNinjas-SDET-123 | team20-GetBatch | Active | 11 |
      