package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Star extends Object {
    public Star(int x, int y) {
        this.x = x;
        this.y = y;

        img = new ImageIcon(getClass().getResource("/image/star.png")).getImage();
        speed = 1;

    }

    public void move() {
        y += speed;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(img, x, y, null);
    }

    public boolean isImpactStarAndPlayer(Car player) {
        Rectangle recStar = new Rectangle(getX(), getY(), img.getWidth(null), img.getHeight(null));

        Rectangle recCar = new Rectangle(player.getX(), player.getY(), 25, 60);
        if (recStar.intersects(recCar)) {
            return true;
        }

        return false;
    }

    public boolean isImpactStarAndBoss(ArrayList<BossCar> arrCar) {
        for (int i = 0; i < arrCar.size(); i++) {
            Rectangle recStar = new Rectangle(getX(), getY(), img.getWidth(null), img.getHeight(null));

            Rectangle recCar = new Rectangle(arrCar.get(i).getX(), arrCar.get(i).getY(), 25, 60);
            if (recStar.intersects(recCar)) {
                return true;
            }
        }
        return false;
    }
}
