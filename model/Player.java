package model;

import javax.swing.*;
import java.awt.*;

public class Player extends Tank {
    public Player(int x, int y) {
        super(x, y);
        orient = UP;
        images = new Image[]{
                new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\player_green_left.png").getImage(),
                new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\player_green_right.png").getImage(),
                new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\player_green_up.png").getImage(),
                new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\tank 90\\src\\images\\player_green_down.png").getImage(),

        };
    }
}
