package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BossCar extends Object {
    private Random rd = new Random();

    public BossCar(int x, int y) {
        this.x = x;
        this.y = y;
        type = rd.nextInt(11) + 4;
        img = new ImageIcon(getClass().getResource("/image/car" + type + ".png")).getImage();

        speed = 1;
    }

    public void move() {
        y += speed;
    }


    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(img, x, y, null);
    }

    public boolean impactBossAndPlayer(Car player) {
        Rectangle recBoss = new Rectangle(getX(), getY(), 25,60);

        Rectangle recPlayer = new Rectangle(player.getX(), player.getY(), 25, 60);
        if (recBoss.intersection(recPlayer).isEmpty() == false) {
            return true;
        }
        return false;
    }

    public boolean checkDie(ArrayList<Bullet> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Rectangle rectangle = getRect().intersection(arr.get(i).getRect());

            if (rectangle.isEmpty() == false) {
                arr.remove(i);
                Sound.play("cham_xe.wav");
                return true;
            }
        }
        return false;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
        return rectangle;
    }




}
