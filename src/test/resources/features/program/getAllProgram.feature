Feature:Program 
  
  Scenario: Get all Programs and do necessary validations
    Given User make a service request with a Base URL 
    When GET http request is made by the User with an endpoint allPrograms
    Then User validate Status code as twohundred
    And response body has "ProgramId", "programName", "programDescription", "programStatus", "creationTime", "lastModTime"