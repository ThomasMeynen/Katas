package scorekeeper;

public class Scorekeeper {
    private int[] score;
    public Scorekeeper() {
        this.score = new int[]{0, 0, 0, 0, 0, 0};
    }
    void scoreTeamA1(){
        score[2] += 1;
        updateScore(2);
    }
    void scoreTeamA2(){
        score[2] += 2;
        updateScore(2);

    }
    void scoreTeamA3(){
        score[2] += 3;
        updateScore(2);

    }
    void scoreTeamB1(){
        score[5] += 1;
        updateScore(5);
    }
    void scoreTeamB2(){
        score[5] += 2;
        updateScore(5);
    }
    void scoreTeamB3(){
        score[5] += 3;
        updateScore(5);
    }
    String getScore(){
        return new StringBuilder().append(score[0]).append(score[1]).append(score[2]).append(":").append(score[3]).append(score[4]).append(score[5]).toString();

    }

    private void updateScore(int start){
        if (score[start] >= 10){
            score[start - 1]++;
            score[start] -= 10;
        }
        if (score[start - 1] >= 10){
            score[start - 2]++;
            score[start - 1] -= 10;
        }
        if (score[start - 2] >= 10){
            throw new ArrayStoreException("The score is too high to use!");
        }
    }

}
