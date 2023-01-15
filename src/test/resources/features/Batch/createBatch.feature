Feature:Batch
  
  Scenario: Create Batch By Batch name as format "Jan23-TeamName-BatchName-serialnumber"
    Given user make a service request with a Base URL request alongwith metadata information in header as Application Json
    And  A payload consist of "batchName", "batchDescription", "batchStatus", "batchNoOfClasses", "programId"
    When POST Request is made by the User with an endpoint "batches"
    Then user validate status code as twohundredone
    And Response body has "batchId", "batchName", "batchDescription", "batchStatus", "batchNoOfClasses", "programId", "programName"
    
    
    
 