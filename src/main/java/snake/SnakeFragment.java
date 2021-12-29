package snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static snake.Game.BOARD_SIZE;

public class SnakeFragment extends Element {
    private SnakeFragment nextFragment = null;
    private Direction direction = Direction.RIGHT;
    private int moveCounter;
    private Direction lastDirection = Direction.RIGHT;

    public SnakeFragment() {
        super(0, 0, Color.BLACK);
    }

    public SnakeFragment(int x, int y) {
        super(x, y, Color.BLACK);
    }

    public void move() {
        moveCounter += 1;
        if (moveCounter == 1) {
            return;
        }
        executeMove();
        reactToWall();
        if (nextFragment != null) {
            nextFragment.direction = lastDirection;
            nextFragment.move();
        }
        lastDirection = direction;
    }

    private void executeMove() {
        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
    }

    @Override
    public String toString() {
        return "SnakeFragment{" +
                "x=" + x +
                ", y=" + y +
                ", nextFragment=" + nextFragment +
                ", direction=" + direction +
                ", moveCounter=" + moveCounter +
                ", lastDirection=" + lastDirection +
                '}';
    }

    private void reactToWall() {
        if (x > BOARD_SIZE) {
            x = 0;
        }
        if (x < 0) {
            x = BOARD_SIZE;
        }
        if (y > BOARD_SIZE) {
            y = 0;
        }
        if (y < 0) {
            y = BOARD_SIZE;
        }
    }

    public void changeDirection(KeyEvent e) {
        int code = e.getKeyCode();
        Direction earlierDirection = direction;
        Direction opositeDirection = Direction.giveOpositeDirection(earlierDirection);

        if (code == KeyEvent.VK_W) {
            direction = Direction.UP;
        } else if (code == KeyEvent.VK_S) {
            direction = Direction.DOWN;
        } else if (code == KeyEvent.VK_D) {
            direction = Direction.RIGHT;
        } else if (code == KeyEvent.VK_A) {
            direction = Direction.LEFT;
        }

        // mam wart odwrotnego kierunku do kierunku jazdy
        // czyli następne direction nie może być równe opositeDirection
        if (direction == opositeDirection) {
            direction = earlierDirection;
        }
    }

    public boolean eat(List<Food> foods){
        return foods.removeIf(food -> x == food.x && y == food.y);
    }

    public void elongate() {
        if (nextFragment != null) {
            nextFragment.elongate();
        } else {
            nextFragment = new SnakeFragment(x, y);
        }
    }

    public boolean isDead() {
        int y = this.y;
        int x = this.x;
        return isThereTail(x, y);
    }

    public boolean isThereSnake(int x, int y) {
        if (this.x == x && this.y == y) {
            return true;
        } else {
            return isThereTail(x, y);
        }
    }

    public boolean isThereTail(int x, int y) {
        SnakeFragment fragment = nextFragment;
        while (fragment != null) {
            if (fragment.x == x && fragment.y == y) {
                return true;
            }
            fragment = fragment.nextFragment;
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (nextFragment != null) {
            nextFragment.render(g);
        }
    }
}
