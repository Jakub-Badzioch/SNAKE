package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Game extends JFrame {
    private int points = 0;
    private FoodFactory foodFactory = new FoodFactory(this);
    private final SnakeFragment head = new SnakeFragment();
    private List<Food> foods = new ArrayList<>();
    private Food food = new Food(15, 15);
    public static final int BOARD_SIZE = 24;
    public static final int FIELD = 25;

    public Game() throws HeadlessException {
        super("snakeFragment.SnakeFragment");
        setSize(BOARD_SIZE * FIELD + (int) (1.5 * FIELD), BOARD_SIZE * FIELD + (int) (2.5 * FIELD));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wyłączenie po nacisnieniciu krzyzyka
        setResizable(false);
        JPanel panel = new GraphicPanel();
        add(panel);
    }

    public boolean checkForSnake(int x, int y) {
        return head.isThereSnake(x, y);
    }

    public class GraphicPanel extends JPanel implements ActionListener, KeyListener {

        private Timer timer = new Timer(250, this);

        public GraphicPanel() {
            timer.start();
            setFocusable(true); //ten element ma priorytet
            addKeyListener(this);
//            snakeFragment.Game.this.addKeyListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            head.render(g);
            head.move();
            if (head.isDead()){
                dispose();
                String name = JOptionPane.showInputDialog(" Koniec Gry, dostałeś " + points + " punktów, podaj Imię: ");
                Record record = new Record(name, points);
                RecordManager recordManager = new RecordManager();
                try {
                    recordManager.saveRecord(record);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            foodFactory.spawnFood(foods);

            for (Food food : foods) {
                food.render(g);
            }
            boolean ate = head.eat(foods);

            if (ate) {
                points++;
                head.elongate();
            }
            // czy przegrałeś?
            // tak? To zamykam


        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            head.changeDirection(e);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
