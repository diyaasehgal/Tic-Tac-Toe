1. How to run program :
   -compile the GameBoard.java file and TicTacToe.java file
    by javac filename.java in terminal.
   -run the program by typing java TicTacToe

2. First the program will ask the user to choose the game play mode.
   integer 1 and 2 are the valid input options.
   rest all will be considered as invalid and the program will terminate.

3. single player- against computer version. The computer uses minimax algo 
   to optimize its move and increase chances if winning.

4. As per the chosen mode, first turn will be of player 1 (X) and 
   the next turn will be either player 2(O) in which the input will be taken
   through the terminal only (an integer between 1-9) or computer(O) which
   has been programmed to play the best optimized moves to assist a win or a tie.

5. Any input except [1-9] will not be accepted. And the program will terminate there.
   Once a position is filled in the game board, it can not be overwritten.

6. To play again, run the program again.
