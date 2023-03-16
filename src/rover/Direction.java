package rover;

import java.util.Arrays;

public enum Direction {
    N("North", new Coordinate(0,-1)),
    E("East", new Coordinate(1, 0)),
    S("South", new Coordinate(0, 1)),
    W("West", new Coordinate(-1, 0));

    private String string;
    private Coordinate translation;

    // constructor to set the string
    Direction(String name, Coordinate translation){
        this.string = name;
        this.translation = translation;
    }

    public Coordinate getTranslation(){ return this.translation; }
    // the toString just returns the given name
    @Override
    public String toString() {
        return string;
    }

    public Direction nextDirection(){
        int index = this.ordinal() + 1;
        index %= values().length;
        return values()[index];
    }

    public Direction previousDirection(){
        int index = this.ordinal() - 1;
        if (index == -1) {index = values().length - 1; }
        return values()[index];
    }
}
