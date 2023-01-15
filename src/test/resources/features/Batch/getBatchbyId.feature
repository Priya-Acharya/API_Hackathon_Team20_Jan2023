Feature:Batch
  
  Scenario: Get Batches By Id and do necessary validations
    Given uSer make a service request with a Base URL 
    When POST Request is made by the User with an endpoint batches slash "batchId"
    Then user validate status code as twohundred
    And Response body has "batchId", "batchName", "batchDescription", "batchStatus", "batchNoOfClasses", "programId", "programName"
    
    
    
