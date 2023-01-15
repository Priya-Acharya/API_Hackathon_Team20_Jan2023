Feature:Program
  
  Scenario: Delete a program by name
    Given  User make a service request with BaseURI
 		When User make a DELETE http request with resource as deletebyprogramName slash "programName"
    Then User validate response as Message: Program with name ProgramName deleted Successfully!  
    And  status code as twohundred OK

 
