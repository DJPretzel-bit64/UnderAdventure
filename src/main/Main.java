package main;

import javax.swing.JFrame;

public class Main {
    public static JFrame window = new JFrame();
    public static void main(String[] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Under Adventure |");
        window.setResizable(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
    public static void setFPS(int FPS) {
        window.setTitle("Under Adventure | " + FPS + " FPS");
    }
}
