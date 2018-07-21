package model;

import javax.swing.*;
import java.awt.*;

public class Road {
    private int x1, x2;
    private int y1, y2;
    private static final int speed = 2;
    private Image imgRoad;

    public Road() {
        imgRoad = new ImageIcon(getClass().getResource("/image/road.png")).getImage();
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = y1 - 842;
    }

    public void move() {

        y1 += speed;
        y2 += speed;

        if (y1 > 0){
            y2 = y1 -842;
        }

        if (y2>0){
            y1 = y2 - 842;
        }
    }

    public void draw(Graphics2D g2D) {

        g2D.drawImage(imgRoad, x1, y1, null);
        g2D.drawImage(imgRoad, x2, y2, null);

    }
}
