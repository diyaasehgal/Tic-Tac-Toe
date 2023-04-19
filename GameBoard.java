import java.util.*;

//gameboard class
public class GameBoard{
    
    //initializing the empty board
    public char[][] gameboard ={{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};;

    //to print the board
    public void printBoard(){
       
        System.out.println(gameboard[0][0] + "|" + gameboard[0][1]  + "|" + gameboard[0][2]);
        System.out.println("-+-+-");
        System.out.println(gameboard[1][0] + "|" + gameboard[1][1]  + "|" + gameboard[1][2]);
        System.out.println("-+-+-");
        System.out.println(gameboard[2][0] + "|" + gameboard[2][1]  + "|" + gameboard[2][2]);
        
    }


    //Instruction board
    public void instructionBoard(){
        System.out.println("1|2|3");
        System.out.println("-+-+-");
        System.out.println("4|5|6");
        System.out.println("-+-+-");
        System.out.println("7|8|9");
    }

    //game play mode and the respective instructions
    public int gameMode(){
        System.out.println("Welcome to Tic-Tac-Toe game.");
        System.out.println("Please choose game play mode.(Enter number) \n1.Single player\t\t2.Two player");

        Scanner scan = new Scanner(System.in);
        int mode = scan.nextInt();
        scan.close();
        if(mode==1){
            System.out.println("Player is X, Computer is O.\nThis is a new game. Board numbers are as follows:");
            instructionBoard();
        }
        else if (mode==2){
            System.out.println("Player1 is X, Player2 is O.\nThis is a new game. Board numbers are as follows:");
            instructionBoard();
        }

        return mode;

    }


    //function to validate a move (prevent over writing)
    public boolean validMove(int row, int col){

        if(gameboard[row][col] != ' '){
            return false;
        }
        return true;
    }


    //for updating the move of human player/players.
    public void gameMoveP2(String user){

        char symbol = ' ';

        if(user.equals("player1")){
            symbol = 'X';
        }
        else if(user.equals("player2")){
            symbol = 'O';
        }

        System.out.print("Enter your desired location [1-9]: ");
        Scanner scan = new Scanner(System.in);
        int move = scan.nextInt();
        scan.close();
        int row = (move -1)/3;
        int col = (move - (row*3))-1 ;

        if(validMove(row,col)){
            gameboard[row][col] = symbol;
        }
        else{
            System.out.println("Invalid move. Position already taken. Try again!");
            gameMoveP2(user);
        }
        
    }


    //winning condition.
    public boolean winGame(){

        //cases- 1,2,3 - 4,5,6 - 7,8,9
        for(int i=0; i<3;i++){
            if(gameboard[i][0]!=' ' && gameboard[i][0]==gameboard[i][1] && gameboard[i][1] == gameboard[i][2]){
                return false;
            }
        }

        //cases - 1,4,7 - 2,5,8 - 3,6,9
        for(int i=0; i<3;i++){
            if(gameboard[0][i]!=' ' && gameboard[0][i] ==gameboard[1][i] && gameboard[1][i] == gameboard[2][i]){
                return false;
            }
        }

        //cases- 1,5,9 - 7,5,3
        if(gameboard[0][0]!=' ' && gameboard[1][1] ==gameboard[0][0] && gameboard[1][1] == gameboard[2][2]){
            return false;
        }

        if(gameboard[0][2]!=' ' && gameboard[1][1] ==gameboard[0][2] && gameboard[1][1] == gameboard[2][0]){
            return false;
        }

        return true;
    }


    //to check tie condition
    public boolean tieGame(){
        for(char[] row: gameboard){
            for(char box:row){
                if (box == ' ')
                return true;
            }
        }
        return false;  
    }


    //for updating computer's move
    public void gameMoveCPU(){

        // //it will first traverse the 2D array for best win possibility
        int move1 = optimizedMoveCPU('O');
        int move2 = optimizedMoveCPU('X');

        
        if(move1>=0){
            gameboard[move1%10][move1/10] = 'O';
        }
        //this will try to block player win possibilities.
        else if(move2>=0){
            gameboard[move2%10][move2/10] = 'O';
        }

        else{
           //this is to generate a random move at an empty spot
            while(true){

                //for random moves
                Random random = new Random();
                int row = random.nextInt(2); 
                int col = random.nextInt(2); 

                if(validMove(row,col)){
                    gameboard[row][col] = 'O';
                    break;
                }
            }   
        }   
    }


    private int optimizedMoveCPU(char symbol){
       
        int row = -1, col = -1 ;
        
        

            //if 2 Xs /Os are diagonally in a line
            if(gameboard[1][1] == symbol && gameboard[1][1] == gameboard[0][0] && (validMove(2,2))){
                row = 2; col=2;
            }
            else if(gameboard[1][1] == symbol && gameboard[1][1] == gameboard[2][2] && (validMove(0,0))){
                row = 0; col= 0;
            }
            else if(gameboard[0][0] == symbol && gameboard[0][0] == gameboard[2][2] && (validMove(1,1))){
                row = 1; col= 1;
            }
            else if(gameboard[1][1] == symbol && gameboard[1][1] == gameboard[0][2] && (validMove(2,0))){
                row= 2; col =0;
            }
            else if(gameboard[0][2] == symbol && gameboard[2][0] == gameboard[0][2] && (validMove(1,1))){
                row= 1; col =1;
            }
            // else if(gameboard[1][1] != ' ' && gameboard[1][1] == gameboard[2][0]){
            //      if(validMove(0,2)){
            //              gameboard[0][2] = 'O';
            //          }
            // }
            else if(gameboard[1][1] == symbol && gameboard[1][1] == gameboard[2][0] && (validMove(0,2))){
                row= 0; col =2;
            }

            else{

                for(int i=0; i<3;i++){

                    //if 2 Xs in a row, then block the third one using an O
                    //if 2 Os in a row, then place the third one to win the game.
                    if(gameboard[i][1] == symbol && gameboard[i][0] == gameboard[i][1] && (validMove(i,2))){
                        row = i; col = 2;
                    }

                    else if(gameboard[i][1] == symbol && gameboard[i][2] == gameboard[i][1] && (validMove(i,0))){
                        row = i;  col = 0;
                    }

                    else if(gameboard[i][0] == symbol && gameboard[i][2] == gameboard[i][0] && (validMove(i,1))){
                        row = i;  col = 1;
                    }

                    //if 2 Xs/Os in a column
                    else if(gameboard[1][i] == symbol && gameboard[0][i] == gameboard[1][i] && (validMove(2,i))){
                        row = 2; col = i;
                    }

                    else if(gameboard[1][i] == symbol && gameboard[2][i] == gameboard[1][i] && (validMove(0,i))){
                        row = 0; col = i;
                    }

                    else if(gameboard[0][i] == symbol && gameboard[0][i] == gameboard[2][i] && (validMove(1,i))){
                        row = 1; col = i;
                    }
                }

            }

        //System.out.println(row+ col*10);
        return row + col*10;
    

    }
    
}

   