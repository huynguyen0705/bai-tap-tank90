package model;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    protected int x;
    protected int y;
    protected int orient;
    protected int speed;
    protected Image image = new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\bullet.png").getImage();
    public Bullet (int x, int y, int orient){
        this.x = x - image.getWidth(null) / 2;
        this.y = y - image.getHeight(null) / 2;
        this.orient = orient;
        this.speed = 2;
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(this.image, x, y, null);
    }
    public boolean move(){
        switch (orient) {
            case Tank.LEFT:
                x -= speed;
                break;
            case Tank.RIGHT:
                x += speed;
                break;
            case Tank.UP:
                y -= speed;
                break;
            case Tank.DOWN:
                y += speed;
                break;
        }
        if (x <= 0 || y <=0){
            return true;
        }else {
            return false;
        }
    }
    public Rectangle getRect(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
