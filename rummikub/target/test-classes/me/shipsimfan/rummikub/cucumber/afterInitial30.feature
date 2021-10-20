Feature: Playing different types of melds after the initial 30 points have been already played

    Background: Play the initial 30 points
        Given the initial 30 points have been played

    Scenario: Playing a valid 3-length run
        When I play "O1" to a new meld
        And I play "O2" to the last played meld
        And I play "O3" to the last played meld
        And I end my turn
        Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9},{O1,O2,O3}"
        And player 1's hand should be "R1,R1,R2,R10,R13,B1,G1,O4,O5,J"
        
    Scenario: Playing a valid 5-length run
    	When I play "O1" to a new meld
    	And I play "O2" to the last played meld
    	And I play "O3" to the last played meld
    	And I play "O4" to the last played meld
    	And I play "O5" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9},{O1,O2,O3,O4,O5}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,G1,J"
    	
    Scenario: Playing a valid run with a joker
    	When I play "O1" to a new meld
    	And I play "J" to the last played meld
    	And I play "O3" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9},{O1,J,O3}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,G1,O2,O4,O5"
    	
    Scenario: Playing a valid 3-length set
    	When I play "R1" to a new meld
    	And I play "B1" to the last played meld
    	And I play "G1" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9},{R1,B1,G1}"
    	And player 1's hand should be "R1,R2,R10,R13,O1,O2,O3,O4,O5,J"
    	
    Scenario: Playing a valid 4-length set
    	When I play "R1" to a new meld
    	And I play "B1" to the last played meld
    	And I play "G1" to the last played meld
    	And I play "O1" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9},{R1,B1,G1,O1}"
    	And player 1's hand should be "R1,R2,R10,R13,O2,O3,O4,O5,J"
    	
    Scenario: Playing a set with a joker
    	When I play "J" to a new meld
    	And I play "R1" to the last played meld
    	And I play "G1" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9},{J,R1,G1}"
    	And player 1's hand should be "R1,R2,R10,R13,B1,O1,O2,O3,O4,O5"
    	
    Scenario: Extending a run
    	When I play "R13" to meld 0
    	And I end my turn
    	Then the board should be "{R10,R11,R12,R13},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,B1,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Extending a set
    	When I play "R10" to meld 1
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10,R10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R13,B1,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Extending a run with a joker
    	When I play "J" to meld 0
    	And I end my turn
    	Then the board should be "{R10,R11,R12,J},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,G1,O1,O2,O3,O4,O5"
    	
    Scenario: Extending a set with a joker
    	When I play "J" to meld 1
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{R1,G1,B1,J},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,G1,O1,O2,O3,O4,O5"
    	
    Scenario: Playing an invalid run by number
    	When I play "O1" to a new meld
    	And I play "O3" to the last played meld
    	And I play "O4" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Playing an invalid run by color
    	When I play "O1" to a new meld
    	And I play "R2" to the last played meld
    	And I play "O3" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Playing a 2-length run
    	When I play "O1" to a new meld
    	And I play "O2" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Playing an invalid set by color
    	When I play "O1" to a new meld
    	And I play "O1" to the last played meld
    	And I play "R1" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Playing an invalid set by number
    	When I play "O1" to a new meld
    	And I play "B1" to the last played meld
    	And I play "R2" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Playing a 2-length set
    	When I play "O1" to a new meld
    	And I play "B1" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Playing a 5-length set
    	When I play "O1" to a new meld
    	And I play "B1" to the last played meld
    	And I play "R1" to the last played meld
    	And I play "G1" to the last played meld
    	And I play "R1" to the last played meld
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Extending a run incorrectly
    	When I play "R1" to meld 0
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Extending a set incorrectly
    	When I play "R1" to meld 1
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	
    Scenario: Extending a 4-length set
    	When I play "R2" to meld 2
    	And I end my turn
    	Then the board should be "{R10,R11,R12},{G10,B10,O10},{R9,G9,B9,O9}"
    	And player 1's hand should be "R1,R1,R2,R10,R13,B1,B10,B11,B12,G1,O1,O2,O3,O4,O5,J"
    	