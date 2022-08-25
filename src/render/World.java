package render;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class World {
    public BufferedImage wall1;
    public Shape world = new Polygon();

    GamePanel gp;
    KeyHandler keyH;

    public World(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        getWorldImages();
    }

    public void getWorldImages() {
        try {
            wall1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/world/wall1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
