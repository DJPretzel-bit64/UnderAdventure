package render;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = gp.screenWidth/2;
        y = gp.screenHeight/2;
        speed = gp.scale * gp.speed;
        direction = "left";
        frameNum = 1;
        step = 1;
        entity = new Rectangle(x + 25, y + 4, 16, 60);
        showHitBoxes = false;
        lastTime = System.nanoTime() + 1000000000;
    }

    public void getPlayerImage() {
        try {
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left4.png")));
            left5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left5.png")));
            left6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left6.png")));
            left7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left7.png")));
            left8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left8.png")));
            left9 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left9.png")));
            left10 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left10.png")));
            left11= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left11.png")));
            left12 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/left12.png")));
            right1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/right1.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/player/up1.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.showHitBoxes && !showHitBoxes && System.nanoTime() > lastTime + 200000000) {
            lastTime = System.nanoTime();
            showHitBoxes = true;
        }

        if(keyH.showHitBoxes && showHitBoxes && System.nanoTime() > lastTime + 200000000) {
            lastTime = System.nanoTime();
            showHitBoxes = false;
        }

        if(keyH.leftPressed || keyH.rightPressed || World.inAir) {
            step ++;
            if(step > 5) {
                if(frameNum == 1) {
                    frameNum = 2;
                }else if(frameNum == 2) {
                    frameNum = 3;
                }else if(frameNum == 3) {
                    frameNum = 4;
                }else if(frameNum == 4) {
                    frameNum = 1;
                }
                step = 0;
            }
        }else {
            frameNum = 1;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "left" -> {
                if(frameNum == 1)image = left1;
                if(frameNum == 2)image = left2;
                if(frameNum == 3)image = left3;
                if(frameNum == 4)image = left4;
                if(frameNum == 5)image = left5;
                if(frameNum == 6)image = left6;
                if(frameNum == 7)image = left7;
                if(frameNum == 8)image = left8;
                if(frameNum == 9)image = left9;
                if(frameNum == 10)image = left10;
                if(frameNum == 11)image = left11;
                if(frameNum == 12)image = left12;
            }
            case "right" -> image = right1;
            case "up" -> image = up1;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        if(showHitBoxes) {
            g2.setColor(Color.orange);
            g2.draw(entity);
        }
    }
}
