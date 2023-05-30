package gui;

import javax.swing.*;

public class CustomFrame extends JFrame {
    public static final int W_FRAME = 510;
    public static final int H_FRAME = 525;
    public CustomFrame(){
        setTitle("tank 90");
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CustomPanel panel = new CustomPanel();
        add(panel);
        setVisible(true);

    }
}
