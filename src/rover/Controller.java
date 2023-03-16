package rover;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    // this has to be an even positive number

    public static void main(String[] args) {
        //initialize our classes and variables
        int planetSize = 20;
        int obstacleAmount = 2;
        Coordinate startCoordinate = new Coordinate(1,1);
        Direction startDirection = Direction.N;

        //populate the obstacle array with random obstacles
        Coordinate[] obstacles = generateObstacles(planetSize, obstacleAmount);

        Rover rover = new Rover(startCoordinate, startDirection, planetSize);
        Scanner sn = new Scanner(System.in);

        boolean moving = true;
        while(moving){
            System.out.println("Input new command: ");
            String input = sn.nextLine();
            if (input.equals("quit")){
                moving = false;
                continue;
            }
            rover.executeCommands(input, obstacles);
            System.out.println(rover);
        }
    }

    private static Coordinate[] generateObstacles(int planetSize, int obstacleAmount) {
        Coordinate[] obstacles = new Coordinate[obstacleAmount];
        StringBuilder show = new StringBuilder();
        show.append("Obstacles placed at: ");
        for (int i = 0; i < obstacles.length; i++) {
            int randomX = ThreadLocalRandom.current().nextInt(0, planetSize + 1);
            int randomY = ThreadLocalRandom.current().nextInt(0, planetSize + 1);
            obstacles[i] = new Coordinate(randomX, randomY);
            show.append("(" + randomX + ", " + randomY + "), ");
        }
        show.append("watch out!");
        System.out.println(show);
        return obstacles;
    }
}
class ObstacleFoundException extends Exception
{
    // Parameterless Constructor
    public ObstacleFoundException() {}

    // Constructor that accepts a message
    public ObstacleFoundException(String message)
    {
        super(message);
    }
}