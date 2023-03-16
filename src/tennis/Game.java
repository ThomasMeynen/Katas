package tennis;

public class Game {
    private Score player1score;
    private Score player2score;

    public boolean hasWinner;

    Game(){
        player1score = Score.LOVE;
        player2score = Score.LOVE;
        hasWinner = false;
    }

    void player1Scores(){
        switch (player1score){
            case ADVANTAGE:
                hasWinner = true;
                break;
            case FORTY:
                if (player2score == Score.ADVANTAGE) {
                    player2score = Score.FORTY;
                } else {
                    player1score = Score.ADVANTAGE;
                }
                break;
            default:
                player1score = getNextScore(player1score);
        }
    }
    void player2Scores() {
        switch (player2score){
            case ADVANTAGE:
                hasWinner = true;
                break;
            case FORTY:
                if (player1score == Score.ADVANTAGE) {
                    player1score = Score.FORTY;
                } else {
                    player2score = Score.ADVANTAGE;
                }
                break;
            default:
                player2score = getNextScore(player2score);
        }
    }
    Score getNextScore(Score e)
    {
        int index = e.ordinal();
        int nextIndex = index + 1;
        Score[] scores = Score.values();
        nextIndex %= scores.length;
        return scores[nextIndex];
    }

    void printScore(){
        if (!hasWinner){
            System.out.println(player1score.toString() + "  " + player2score.toString() + "!");
            return;
        }
        if (player1score == Score.ADVANTAGE){
            System.out.println("Player 1 wins!");
            return;
        }
        System.out.println("Player 2 wins!");
    }
}


enum Score{
    LOVE("Love"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("Adv.");

    // Member to hold the name
    private String string;

    // constructor to set the string
    Score(String name){string = name;}

    // the toString just returns the given name
    @Override
    public String toString() {
        return string;
    }

}