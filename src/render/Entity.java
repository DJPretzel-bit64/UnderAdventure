package render;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public static int x, y, speed, frameNum, step, timeInAir, velocityY;
    public double velocity;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8, left9, left10, left11, left12, right1, up1;
    public boolean inAir = false;
    public String direction;
    public Shape entity;
}
