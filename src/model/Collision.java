package model;

import javax.swing.*;
import java.awt.*;

public class Collision {
    private int x;
    private int y;
    private int index = -1;
    private int tour = 10;
    private Image[] images = {
            new ImageIcon(getClass().getResource("/image/boom1.png")).getImage(),
            new ImageIcon(getClass().getResource("/image/boom2.png")).getImage(),
            new ImageIcon(getClass().getResource("/image/boom3.png")).getImage(),
            new ImageIcon(getClass().getResource("/image/boom4.png")).getImage(),
            new ImageIcon(getClass().getResource("/image/boom5.png")).getImage(),
    };

    public Collision(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean draw(Graphics2D g2D) {
        index++;
        if (index >= images.length) {
            index = 0;
            tour--;
        }
        g2D.drawImage(images[index], x-images[index].getWidth(null), y-images[index].getHeight(null), null);
        return tour == 0;
    }
}
