 
 Feature:Batch
  
  Scenario: Create Batch and do necessay validations
    Given Service request with base url and payload with batchName, batchDescription, batchStatus, batchNoOfClasses, programId
    When POST request is made with Endpoint batches
    Then User validate Status and response body with batchID, batchName, batchDescription, batchStatus, batchNoOfClasses, programId, programName
    
    
    Scenario: Create Batch two and do necessay validations
   	Given Service request with base url and payload with batchName, batchDescription, batchStatus, batchNoOfClasses, programId forsecondbatch
    When POST request is made with Endpoint batches forsecondbatch
    Then User validate Status and response body with batchID, batchName, batchDescription, batchStatus, batchNoOfClasses, programId, programName forsecondbatch
    