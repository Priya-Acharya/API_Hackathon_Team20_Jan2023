#Author:  Kalaipriya
#Keywords Summary :The user can update a program Details based on Program ID and Program Name.

Feature: Update Program by Program ID and Program name

   Background: 
    Given  A Program exists
   	
  @tag2
    Scenario Outline: Update Program by Program Id
      When The user makes a PUT request for the program to update "<desc>"  and "<progstatus>"
      Then Validate the status code and the updated program details as "<desc>" and "<progstatus>"

		 Examples: 
    | desc | progstatus|
    | updateprogram | InActive |
      

	@tag3
    Scenario Outline: Update Program by Program Name
      When The user makes a PUT request for the program to update "<progName>","<desc>" and "<progstatus>"
      Then Validate the status code and the updated program details as "<progName>","<desc>" and "<progstatus>"

		Examples: 
     | progName | desc | progstatus|
     | Jan23-CRUD-Update | updateprogram2 | Active |
      