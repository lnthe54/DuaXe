package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Coins extends Object {

    public Coins(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 1;
        img = new ImageIcon(getClass().getResource("/image/coins.png")).getImage();
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(img, x, y, 30, 30, null);
    }

    public void move() {
        y += speed;
    }

    public boolean impactCoinAndPlayer(Car player) {

        Rectangle recCoin = new Rectangle(getX(), getY(), 30, 30);

        Rectangle recPlayer = new Rectangle(player.getX(), player.getY(), 25, 67);
        if (recCoin.intersects(recPlayer)) {
            return true;
        }
        return false;
    }

    public boolean impactCoinAndBullet(ArrayList<Bullet> arrBullet ){
        for (int i = 0; i < arrBullet.size(); i++){
            Rectangle recCoin = new Rectangle(getX(),getY(), 30,30);

            Rectangle recBullet = new Rectangle(arrBullet.get(i).getX(), arrBullet.get(i).getY(), 50,50);

            if (recCoin.intersects(recBullet)){
                return true;
            }
        }
        return false;
    }

}
