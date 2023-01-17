Feature:Batch
  
  Scenario: Get Batches By Id and do necessary validations
    Given uSer make a service request with a Base URL 
    When GET Request is made by the User with an endpoint batches slash "batchId"
   	Then  User validate Statuscode with response body as "batchId", "batchName", "batchDescription", "batchStatus", "batchNoOfClasses", "programId", "programName"
