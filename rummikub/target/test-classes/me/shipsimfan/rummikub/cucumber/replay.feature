Feature: Replaying tiles from melds
	Background: Play 3 rounds
		Given 3 rounds have been played
		
	Scenario: Replaying tiles from a set to create a meld
		When I replay "R13" from meld 1 to a new meld
		And I play "B13" to the last played meld
		And I play "G13" to the last played meld
		And I end my turn
		Then the board should be "{B11,G11,O11},{J,R8,R9,R10,R11,R12},{R10,R11,R12},{R1,B1,G1},{R13,B13,G13}"
		And player 1's hand should be "G1,G10,O1,O10"
	
	Scenario: Replaying tiles from a run to extend a meld
		When I replay "R13" from meld 1 to meld 2
		And I end my turn
		Then the board should be "{B11,G11,O11},{J,R8,R9,R10,R11,R12},{R10,R11,R12,R13},{R1,B1,G1}"
		And player 1's hand should be "B13,G1,G10,G13,O1,O10"
	
	Scenario: Replaying a joker
		When I replay "J" from meld 1 to meld 0
		And I end my turn
		Then the board should be "{B11,G11,O11,J},{R8,R9,R10,R11,R12,R13},{R10,R11,R12},{R1,B1,G1}"
		And player 1's hand should be "B13,G1,G10,G13,O1,O10"
	
	Scenario: Replaying from a 3-length set
		When I replay "R1" from meld 3 to a new meld
		And I play "G1" to the last played meld
		And I play "O1" to the last played meld
		And I end my turn
		Then the board should be "{B11,G11,O11},{J,R8,R9,R10,R11,R12,R13},{R10,R11,R12},{R1,B1,G1}"
		And player 1's hand should be "B13,G1,G2,G3,G4,G10,G13,O1,O10"
	
	Scenario: Replaying from a 3-length run
		When I replay "R11" from meld 2 to meld 0
		And I end my turn
		Then the board should be "{B11,G11,O11},{J,R8,R9,R10,R11,R12,R13},{R10,R11,R12},{R1,B1,G1}"
		And player 1's hand should be "B13,G1,G2,G3,G4,G10,G13,O1,O10"
	
	Scenario: Replay an invalid tile from the middle of a run
		When I replay "R11" from meld 2 to meld 0
		And I end my turn
		Then the board should be "{B11,G11,O11},{J,R8,R9,R10,R11,R12,R13},{R10,R11,R12},{R1,B1,G1}"
		And player 1's hand should be "B13,G1,G2,G3,G4,G10,G13,O1,O10"
	
	Scenario: Replay a valid tile from the middle of a run and split the run
		When I play "O10" to a new meld
		And I replay "R10" from meld 1 to the last played meld
		And I play "G10" to the last played meld	
		And I end my turn
		Then the board should be "{B11,G11,O11},{J,R8,R9},{R10,R11,R12},{R1,B1,G1},{O10,R10,G10},{R11,R12,R13}"
		And player 1's hand should be "B13,G1,G13,O1"
	
	Scenario: Replay into an invalid meld
		When I replay "R12" from meld 2 to a new meld
		And I end my turn
		Then the board should be "{B11,G11,O11},{J,R8,R9,R10,R11,R12,R13},{R10,R11,R12},{R1,B1,G1}"
		And player 1's hand should be "B13,G1,G2,G3,G4,G10,G13,O1,O10"
	
	
	