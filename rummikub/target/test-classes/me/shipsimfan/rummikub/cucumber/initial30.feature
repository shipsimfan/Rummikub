Feature: Playing before the initial 30 points have been earned

	Background: Create the game
		Given the game has started
		
	Scenario: Not playing and drawing
		When I end my turn
		Then the board should be ""
		And player 1's hand should be "R1,R9,R10,R11,R12,B1,B9,B10,G9,O1"
		And player 1 should have 30 points remaining
	
	Scenario: Playing one meld worth at least 30 points
		When I play "R9" to a new meld
		And I play "R10" to the last played meld
		And I play "R11" to the last played meld
		And I play "R12" to the last played meld
		And I end my turn
		Then the board should be "{R9,R10,R11,R12}"
		And player 1's hand should be "R1,B1,B9,G9,O1"
		And player 1 should have 0 points remaining
		
	Scenario: Playing more than one meld worth at least 30 points
		When I play "R1" to a new meld
		And I play "B1" to the last played meld
		And I play "O1" to the last played meld
		And I play "R9" to a new meld
		And I play "G9" to the last played meld
		And I play "B9" to the last played meld
		Then the board should be "{R1,B1,O1},{R9,G9,B9}"
		And player 1's hand should be "R10,R11,R12"
		And player 1 should have 0 points remaining
		
	Scenario: Playing a meld worth less than thirty points
		When I play "R1" to a new meld
		And I play "B1" to the last played meld
		And I play "O1" to the last played meld
		And I end my turn
		Then the board should be ""
		And player 1's hand should be "R1,R9,R10,R11,R12,B1,B9,B10,B11,B12,G9,O1"
		And player 1 should have 30 points remaining
		
	Scenario: Playing invalid melds worth at least 30 points
		When I play "R1" to a new meld
		And I play "R9" to a new meld
		And I play "R11" to the last played meld
		And I play "R12" to the last played meld
		And I end my turn
		Then the board should be ""
		And player 1's hand should be "R1,R9,R10,R11,R12,B1,B9,B10,B11,B12,G9,O1"
		And player 1 should have 30 points remaining