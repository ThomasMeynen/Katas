package rover;

public class Rover {
    private Coordinate coordinate;
    private Direction direction;
    private int planetSize;

    public Rover(Coordinate coordinate, Direction direction, int planetSize) {
        this.coordinate = coordinate;
        this.direction = direction;
        this.planetSize = planetSize;
    }

    public void executeCommands(String commands, Coordinate[] obstacles){
        for (char command :
                commands.toCharArray()) {
            switch (command){
                case 'f' -> {
                    try { moveForward(obstacles); }
                    catch (ObstacleFoundException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                case 'b' -> {
                    try { moveBackward(obstacles); }
                    catch (ObstacleFoundException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                case 'r' -> turnRight();
                case 'l' -> turnLeft();
                default -> System.out.println("Invalid command found...\n Skipping and moving to next command.");
            }
        }
    }

    public void moveForward(Coordinate[] obstacles) throws ObstacleFoundException {
        Coordinate newLocation = this.coordinate.addCoordinates(this.direction
                .getTranslation());
        Coordinate obstacle = checkObstacles(newLocation, obstacles);
        if (obstacle != null){
            throw new ObstacleFoundException("An obstacle was found at: ("
                    + obstacle.x() + ", " + obstacle.y()
                    + "). Aborting command sequence!");
        }
        this.coordinate = newLocation;
        this.updatePosition();
    }

    public void moveBackward(Coordinate[] obstacles) throws ObstacleFoundException {
        Coordinate newLocation = this.coordinate.addCoordinates(this.direction
                .nextDirection()
                .nextDirection()
                .getTranslation());
        Coordinate obstacle = checkObstacles(newLocation, obstacles);
        if (obstacle != null){
            throw new ObstacleFoundException("An obstacle was found at: ("
                    + obstacle.x() + ", " + obstacle.y()
                    + "). Aborting command sequence!");
        }
        this.coordinate = newLocation;
        this.updatePosition();
    }

    public void updatePosition(){
        if (this.coordinate.y() < 0){
            this.coordinate = this.coordinate.addCoordinates(new Coordinate(-(planetSize/2), 1));
            this.direction = direction.nextDirection().nextDirection();
        }
        if (this.coordinate.y() > planetSize - 1){
            this.coordinate = this.coordinate.addCoordinates(new Coordinate(-(planetSize/2), -1));
            this.direction = direction.nextDirection().nextDirection();
        }
        if (this.coordinate.x() < 0){
            this.coordinate = this.coordinate.addCoordinates(new Coordinate(planetSize, 0));
        }
        if (this.coordinate.x() > planetSize - 1){
            this.coordinate = this.coordinate.addCoordinates(new Coordinate(-planetSize, 0));
        }
    }

    public Coordinate checkObstacles(Coordinate newLocation, Coordinate[] obstacles){
        for (Coordinate obstacle :
                obstacles) {
            if (obstacle.equals(newLocation)) {
                return obstacle;
            }
        }
        return null;
    }
    public void turnRight(){
        this.direction = this.direction.nextDirection();
    }

    public void turnLeft(){
        this.direction = this.direction.previousDirection();
    }
    @Override
    public String toString() {
        return "The rover is at position (" + coordinate.x() + ", " + coordinate.y() + ") and facing " + direction + ".";
    }



}

