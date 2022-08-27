package render;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static render.Entity.speed;

public class World {
    public BufferedImage wall1;
    public Polygon world = new Polygon();
    public Shape worldBox = world;
    public int[][] rawWorld = new int[64][64];
    public int offsetX, offsetY, timeInAir, velocityY;
    public static boolean inAir, falling;
    public double velocity;

    GamePanel gp;
    KeyHandler keyH;

    public World(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getWorldImages();
    }

    public void setDefaultValues() {
        for(int j = 0; j < 64; j++) {
            for(int i = 0; i < 64; i++) {
                rawWorld[i][j] = 0;
                if(i == 0 || i == 63 || j == 0 || j == 63 || j == 6) rawWorld[i][j] = 1;
                if((j == 63 && i <= 10) || j == 6) world.addPoint(i * 64, j * 64);
            }
        }
        offsetX = 0;
        offsetY = 0;
        inAir = true;
        falling = true;
        velocity = 0;
        timeInAir = 0;
        velocityY = offsetY;
    }

    public void update() {
        if(keyH.leftPressed) {
            offsetX += speed;
            Player.direction = "left";
        }

        if(keyH.rightPressed) {
            offsetX -= speed;
            Player.direction = "right";
        }

        if(keyH.upPressed && !inAir) {
            inAir = true;
            falling = false;
            Player.direction = "up";
        }

        if(inAir) {
            if(!falling) velocity = -(10.00/63.00)*(timeInAir * timeInAir) + 6.17 * timeInAir;
            else velocity = -(10.00/63.00)*(timeInAir * timeInAir);
            offsetY = (int) (velocityY + velocity);
            timeInAir++;
        }

        world.reset();
        for(int j = 0; j < 64; j++) {
            for(int i = 0; i < 64; i++) {
                if(j == 63 || (j == 6 && i <= 10)) world.addPoint(i * 64 + offsetX, j * 64 + offsetY);
            }
        }

        if(worldBox.intersects((Rectangle2D) Player.entity)){
            inAir = false;
            velocity = 0;
            timeInAir = 0;
        }
    }

    public void getWorldImages() {
        try {
            wall1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/world/wall1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g2) {
        BufferedImage image = wall1;
        for(int i = 0; i < 64; i++) {
            for(int j = 0; j < 64; j++) {
                if(rawWorld[i][j] == 1)g2.drawImage(image, i * 64 + offsetX, j * 64 + offsetY, null);
            }
        }
        if(Player.showHitBoxes) {
            g2.setColor(Color.GREEN);
            g2.draw(world);
        }
    }
}
