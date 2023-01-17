#Author: Kalaipriya
#Keywords Summary : User can retrieve specific program based on Program ID.Get Program by using programId

Feature: Get Program by using programId 


  @tag1
  Scenario Outline: Get Program by Id
    Given  Program with name "<program_name>", decription "<description>" and status "<status>" exists
    When The user makes a GET request for the program
    Then The returned program has name as "<program_name>", decription as "<description>" and status as "<status>"

		Examples: 
      | program_name  | description | status  |
      | Jan23-CRUDInterfacingNinjas-SDET-321 | team20-GetProgram | Active |
      
