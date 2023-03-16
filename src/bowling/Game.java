package bowling;

import java.util.Arrays;

public class Game {
    private Roll[] rolls;
    private int currentRoll;
    Game() {
        rolls = new Roll[21];
        currentRoll = 0;
    }
    void roll(int rollScore) {
        // check if the roll is a valid roll
        if (rollScore > 10 || rollScore < 0){
            throw new IllegalArgumentException("You cannot roll more than 10 or less than 0 pins in 1 roll!");
        }
        // check if we are on the second roll of the frame
        if (currentRoll%2 == 1 || currentRoll == rolls.length - 1){
            // we keep track of the amount of pins left after the last roll
            int valueOfLastRoll = rolls[currentRoll - 1].getValue();
            int pinsStillStanding = 10 - valueOfLastRoll;

            // If we are in the last frame we want to continue after a strike or spare so we have to set the pins upright again...
            if (rolls[currentRoll - 1].equals(Roll.STRIKE) || rolls[currentRoll -1].equals(Roll.SPARE)) { pinsStillStanding = 11;}

            // we check if we don't roll more pins than those standing
            if (rollScore > pinsStillStanding){
                throw new IllegalArgumentException("Only " + pinsStillStanding + " pins are still standing!");
            }

            // if we roll the exact amount we have rolled a spare
            if (rollScore == pinsStillStanding){
                rolls[currentRoll] = Roll.SPARE.setSpare(rollScore);
                currentRoll++;
                return;
            }
        }
        // set the value of the roll
        rolls[currentRoll] = Roll.byInt(rollScore);

        // if we throw a strike add an empty roll
        if (rollScore == 10 && currentRoll < 18){
            currentRoll++;
            rolls[currentRoll] = Roll.EMPTY;
        }

        // if we are on the second to last roll we need to check if we rolled a strike or nothing. If we rolled a nothing we add an empty to the final roll.
        if (currentRoll == rolls.length - 2 && !(rolls[currentRoll - 1].equals(Roll.STRIKE))){
            currentRoll++;
            rolls[currentRoll] = Roll.EMPTY;
        }

        // go to the next roll
        currentRoll++;
    }

    int score() {
        //keep track of the score
        int score = 0;

        //only calculate if at least 1 roll was made
        if (currentRoll == 0) { return 0;}

        // we will iterate backwards through the array of rolls and keep track of the "next" 2 rolls for each roll
        int nextRoll = 0;
        int nextNextRoll = 0;

        // we iterate backwards
        for (int i = currentRoll - 1; i >= 0; i--) {
            //get the current roll
            Roll thisRoll = rolls[i];

            //check if the roll is empty, if it is we skip it
            if (thisRoll.equals(Roll.EMPTY)){
                continue;
            }

            //check if this roll is a spare if it is we add the next roll value to the score
            if (thisRoll.getString().equals(Roll.SPARE.getString())){
                score += nextRoll;
            }

            //check if the roll is a strike and not one of the last 2 rolls, if it is we add the next 2 roll values to the score
            if (thisRoll.equals(Roll.STRIKE) && i < rolls.length - 2){
                score += nextRoll;
                score += nextNextRoll;
            }

            //update the nextRoll and nextNextRoll
            nextNextRoll = nextRoll;
            nextRoll = thisRoll.getValue();

            // skip adding score if we threw a strike on the second to last roll
            if (thisRoll.equals(Roll.STRIKE) && i == rolls.length - 2){
                continue;
            }
            //update the score with the current roll if it is not the last roll
            if (i < rolls.length - 1){
                score += thisRoll.getValue();
            }
        }
        return score;
    }

    int getCurrentRoll(){
        return currentRoll;
    }

    @Override
    public String toString() {
        return "Game{" +
                "rolls=" + Arrays.toString(rolls) +
                ", currentRoll=" + currentRoll +
                '}';
    }
}

enum Roll {
    EMPTY(" ", -1),
    ZERO("0", 0),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    STRIKE("X", 10),
    SPARE("/", 11);



    // Member to hold the name
    private String string;
    private int value;

    // constructor to set the string
    Roll(String name, int value) {
        string = name;
        this.value = value;
    }

    // the toString just returns the given name
    @Override
    public String toString() {
        return string;
    }
    public int toScore() {return value;}

    public Roll setSpare(int value){
        this.value = value;
        return this;
    }

    public int getValue(){
        return this.value;
    }

    public String getString(){
        return this.string;
    }

    public static Roll byInt(int id) {
        for (Roll roll: values()) {
            if (roll.value == id) {
                return roll;
            }
        }
        throw new IllegalArgumentException("Invalid Roll value: " + id);
    }

}

