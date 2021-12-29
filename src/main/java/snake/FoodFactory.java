package snake;

import java.util.List;
import java.util.Random;

import static snake.Game.BOARD_SIZE;

public class FoodFactory {
    private Game game;
    private int amountOfSpawnedFood = 0;


    public FoodFactory(Game game) {
        this.game = game;
    }

    public List<Food> spawnFood(List<Food> foods) {
        amountOfSpawnedFood += 1;
        int frequency = 5;
        if (amountOfSpawnedFood >= frequency) {
            amountOfSpawnedFood = 0;
            executeSpawnedFood(foods);
        }
        return foods;
    }

    public void executeSpawnedFood(List<Food> foods) {
        Random random = new Random();
        int y = random.nextInt(BOARD_SIZE);
        int x = random.nextInt(BOARD_SIZE);
        // if(snake.x == x && snake.y == y){
        // return;
        if (!game.checkForSnake(x, y)) {
            Food food = new Food(x, y);
            foods.add(food);
        } else {
            executeSpawnedFood(foods);
        }
    }
}