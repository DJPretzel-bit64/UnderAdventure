package main;

import render.Player;
import render.World;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int windowSize = 1;
    public final int originalTileSize = 64;
    public final int scale = 1;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16 * windowSize;
    public final int maxScreenRow = 9 * windowSize;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int speed = 6;
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    World world = new World(this, keyH);
    Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000F / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000) {
                Main.setFPS(drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        world.update();
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        world.render(g2);
        player.draw(g2);
        g2.dispose();
    }
}
