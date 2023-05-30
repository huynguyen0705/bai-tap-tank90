package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map {
    private int x;
    private int y;
    private int bit;
    private Image[] images = new Image[]{
            new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\brick.png").getImage(),
            new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\rock.png").getImage(),
            new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\bird.png").getImage(),
            new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\tree.png").getImage(),
            new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\water.png").getImage()

    };

    public Map(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
    }

    public void draw(Graphics2D g2d) {
        if (bit > 0 ) {
            if (bit == 3){
                g2d.drawImage(images[bit - 1], x, y, 38, 38, null);
            }else {
                g2d.drawImage(images[bit - 1], x, y, null);
            }
        }
    }
    public Rectangle getRect(){
        Rectangle rectangle;
        if (bit == 0 || bit == 4){
            rectangle = new Rectangle();
        } else if (bit == 3){
            rectangle = new Rectangle(x, y, 38, 38);
        }else {
            rectangle = new Rectangle(x, y, images[bit - 1].getWidth(null), images[bit - 1].getHeight(null));
        }
        return rectangle;
    }
    public boolean checkBulletPlayer(ArrayList<Bullet> playerBullet){
        for (int i =0; i < playerBullet.size(); i++){
            Rectangle rectangle = getRect().intersection(playerBullet.get(i).getRect());
            if (!rectangle.isEmpty()) {
                if (bit == 4 || bit == 5){
                    return true;
                }
                playerBullet.remove(i);
                if (bit == 3) {
                    System.out.println();
                    return false;
                }
                if (bit == 1) {
                    bit = 0;
                }
                return true;
            }
        }
        return true;
    }
    public boolean checkBulletBot(ArrayList<Bullet> botBullet){
        for (int i =0; i < botBullet.size(); i++){
            Rectangle rectangle = getRect().intersection(botBullet.get(i).getRect());
            if (!rectangle.isEmpty()) {
                if (bit == 4 || bit == 5){
                    return true;
                }
                botBullet.remove(i);
                if (bit == 3) {
                    return false;
                }
                if (bit == 1) {
                    bit = 0;
                }
                return true;
            }
        }
        return true;
    }
}
