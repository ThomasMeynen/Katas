package scorekeeper;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scorekeeper scorekeeper = new Scorekeeper();
        while(true){
            Thread.sleep(200);
            scorekeeper.scoreTeamB3();
            System.out.println(scorekeeper.getScore());
        }
    }
}
