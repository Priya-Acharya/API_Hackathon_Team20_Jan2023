Feature:Program
  
  Scenario: Delete a program by Program ID
    Given  USER make a service request with BaseURI
 		When User make a DELETE http request with resource as deletebyprogid slash "programID"
    Then User validate response as Message: Program with iD ProgramID deleted Successfully!  
    And response status code as twohundred OK

 
