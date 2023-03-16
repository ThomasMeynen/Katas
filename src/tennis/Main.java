package tennis;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        Game tennisGame = new Game();
        tennisGame.printScore();
        while (!tennisGame.hasWinner){
            System.out.println("Who scores?");
            String answer = sn.nextLine();
            if (answer.equals("1")){
                tennisGame.player1Scores();
                tennisGame.printScore();
            } else if (answer.equals("2")){
                tennisGame.player2Scores();
                tennisGame.printScore();
            } else {
                System.out.println("That is not a player that is playing!");
            }
        }
    }
}
