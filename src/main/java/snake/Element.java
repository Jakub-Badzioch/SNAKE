package snake;

import java.awt.*;

public class Element {
    protected int x;
    protected int y;
    private Color color;
    private static final int SIZE = Game.FIELD;

    public Element(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);

    }


}
