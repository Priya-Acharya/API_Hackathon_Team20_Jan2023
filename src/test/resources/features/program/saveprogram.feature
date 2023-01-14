Feature:Program 
  
  Scenario: Create Program By Program name as format "Jan23-TeamName-ProgramName-serialnumber"
    Given User make a service request with a Base URL request alongwith metadata information in header as Application Json
    And  a payload consist of "programName", "ProgramDescription", "programStatus", "creationTime", "lastModTime"
    When POST request is made by the User with an endpoint saveprogram
    Then User validate Status code as twohundredone
    And response body has "ProgramId", "programName", "programDescription", "programStatus", "creationTime", "lastModTime"