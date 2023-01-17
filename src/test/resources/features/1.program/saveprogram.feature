Feature:Program 
  
  Scenario: Create Program By Program name as format "Jan23-TeamName-ProgramName-serialnumber"
    Given User make a service request with a Base URL request alongwith metadata information in header as Application Json
    And  a payload consist of programName, ProgramDescription, programStatus, creationTime, lastModTime
    When POST request is made by the User with an endpoint saveprogram
    Then User validate Status code as twohundredone
    And response body has ProgramId, programName, programDescription, programStatus, creationTime, lastModTime
    
    
    
    Scenario: Create Program By Program name with null value
    Given User make a service request with a Base URL request alongwith metadata information in header as Application Json
    And  a payload consist of programName as null value, ProgramDescription, programStatus, creationTime, lastModTime
    When POST request is made by the User with an endpoint saveprogram with null value
    Then User validate Status code as fourhundred
    And response body has error message must not be null
    
    
     Scenario: Create Program By Program name with multiple space 
    Given User make a service request with a Base URL request alongwith metadata information in header as Application Json
    And  a payload consist of programName with empty data, ProgramDescription, programStatus, creationTime, lastModTime
    When POST request is made by the User with an endpoint saveprogram with empty value
    Then User validate Status code as FourHundred
    And response body has error message programe name must not be empty string
    
    