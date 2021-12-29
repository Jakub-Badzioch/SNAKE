package snake;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->run());
    }

    public static void run(){
        JFrame window = new Game();
//        button.setBounds(100,100,100, 29); // x = lewo i prawo y = góra i dół
//        button.addActionListener((event)-> System.out.println("hello"));
    }
}
