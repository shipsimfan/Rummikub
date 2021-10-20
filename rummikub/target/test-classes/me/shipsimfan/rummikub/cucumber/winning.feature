Feature: Playing the last tiles in your hand and winning the game with scores

	Background: Play four rounds
		Given player 2 is about to win the game
		
	Scenario: Winning the game with a valid meld
		When I play "O9" to a new meld
		And I play "O10" to the last played meld
		And I play "O11" to the last played meld
		And I end my turn
		Then the board should be "{R10,G10,B10},{G8,G9,G10,G11},{B10,B11,B12},{O9,O10,O11}"
		And player 2's hand should be ""
		And player 2 should be the winner
		And player 1 should have a score of -42
		And player 2 should have a score of 0
		And player 3 should have a score of -35
		
	Scenario: Emptying hand with an invalid meld
		When I play "O10" to a new meld
		And I play "O11" to the last played meld
		And I play "O9" to the last played meld
		And I end my turn
		Then the board should be "{R10,G10,B10},{G8,G9,G10,G11},{B10,B11,B12}"
		And there should be no winner
		And player 2's hand should be "B10,B11,B12,O9,O10,O11"