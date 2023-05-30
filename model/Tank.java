package model;

import gui.CustomFrame;

import java.awt.*;
import java.util.ArrayList;

public class Tank {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    protected int x;
    protected int y;
    protected int orient;
    protected int speed;
    protected Image[] images;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 1;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(images[orient], x, y, null);
    }

    public void changeOrient(int newOrient) {
        orient = newOrient;
    }

    public void move(ArrayList<Map> maps) {
        int xRaw = x;
        int yRaw = y;
        switch (orient) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        boolean check = checkMap(maps);
        if (!check){
            x = xRaw;
            y = yRaw;
        }
        else if (x < 0) {
            x = 0;
        } else if (y < 0) {
            y = 0;
        } else if (x > CustomFrame.W_FRAME - images[orient].getWidth(null) - 11) {
            x = CustomFrame.W_FRAME - images[orient].getWidth(null) - 11;
        } else if (y > CustomFrame.H_FRAME - images[orient].getHeight(null) - 35) {
            y = CustomFrame.H_FRAME - images[orient].getHeight(null) - 35;
        }
    }

    public Bullet fire(){
        int x = this.x + images[orient].getWidth(null) / 2;
        int y = this.y + images[orient].getHeight(null) / 2;
        return new Bullet(x ,y, orient);
    }
    public Rectangle getRect(){
        int x = this.x;
        int y = this.y;
        int w = images[orient].getWidth(null);
        int h = images[orient].getHeight(null);
        if (orient == LEFT || orient == RIGHT){
            h -= 2;
        } else if (orient == UP || orient == DOWN) {
            w -= 2;
        }
        return new Rectangle(x, y, w, h);
    }
    public boolean checkMap(ArrayList<Map> maps){
        for (Map map : maps){
            Rectangle rectangle = getRect().intersection(map.getRect());
            if (!rectangle.isEmpty()){
                return false;
            }
        }
        return true;
    }
    public boolean checkDie(ArrayList<Bullet> bullets){
        for (int i = 0; i < bullets.size(); i++){
            Rectangle rectangle = getRect().intersection(bullets.get(i).getRect());
            if (!rectangle.isEmpty()){
                bullets.remove(i);
                return false;
            }
        }
        return true;
    }
}
