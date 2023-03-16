package bowling;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        Game bowlingMatch = new Game();
        Scanner sn = new Scanner(System.in);
/*
        Roll hi = Roll.SPARE.setSpare(1);
        for (int i = 0; i < 18; i++) {
            bowlingMatch.roll(ThreadLocalRandom.current().nextInt(0, 5 + 1));
            System.out.println(bowlingMatch.toString());

        }*/
        while (bowlingMatch.getCurrentRoll() < 21){
            System.out.println(bowlingMatch.toString());
            String roll = sn.nextLine();
            if (roll.equals("score")){
                System.out.println(bowlingMatch.score());
                continue;
            }
            bowlingMatch.roll(parseInt(roll));
        }
        System.out.println(bowlingMatch.toString());
        System.out.println("The final score is: " + bowlingMatch.score());
    }
}
