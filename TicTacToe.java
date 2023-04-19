public class TicTacToe{
        public static void main(String[] args){

            GameBoard b1 = new GameBoard();
            b1.printBoard();
            int choice= b1.gameMode();

            if(choice ==2){

                while(true){
                    System.out.print("Player1 turn. ");
                    b1.gameMoveP2("player1");
                    b1.printBoard();
                    if (!b1.winGame()){
                        System.out.println("Player 1 Wins!!!");
                        break;}
                    if (!b1.tieGame()){
                        System.out.println("It's a tie!!!");
                        break;}
                    
                    System.out.print("Player2 turn. ");
                    b1.gameMoveP2("player2");
                    b1.printBoard();
                    if (!b1.winGame()){
                        System.out.println("Player 2 Wins!!!");
                        break;}
                    if (!b1.tieGame()){
                        System.out.println("It's a tie!!!");
                        break;}
                }
            }

            else if(choice==1){

                while(true){
                    System.out.print("Player turn. ");
                    b1.gameMoveP2("player1");
                    if (!b1.winGame()){
                        b1.printBoard();
                        System.out.println("Player Wins!!!");
                        break;}
                    if (!b1.tieGame()){
                        b1.printBoard();
                        System.out.println("It's a tie!!!");
                        break;}

                    b1.gameMoveCPU();
                    b1.printBoard();
                    if (!b1.winGame()){
                        System.out.println("You lose. Better luck next time!!");
                        break;}
                    if (!b1.tieGame()){
                        System.out.println("It's a tie!!!");
                        break;}
                }

            }
            else{
                System.out.println("**INVALID INPUT!!**");
            }
        }
    }