package snake;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;
// TODO
    // Powtórzyć enumy
    // Przejdź tutorial w linku https://libgdx.com/dev/setup/
    //można dopisać metodę "dajPrzeciwnyKierunek" która zwraca przeciwny direction do tego na którym wywołana

    public static Direction giveOpositeDirection(Direction direction){

        if (direction == Direction.UP){
            return Direction.DOWN;
        } else if (direction == Direction.DOWN){
            return Direction.UP;
        } else if (direction == Direction.RIGHT){
            return Direction.LEFT;
        } else if (direction == Direction.LEFT){
            return Direction.RIGHT;
        }

        return direction;
    }
}
