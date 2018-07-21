package model;

import gui.CarFrame;

import java.awt.*;
import java.util.ArrayList;

public class Car extends Object {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    protected int orient;
    protected Image images;

    private long t;

    public Car(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void move() {
        int speed = 1;
        int xR = x;
        switch (orient) {
            case LEFT: {
                x -= speed;
                break;
            }
            case RIGHT: {
                x += speed;
                break;
            }
        }

        if (x <= 130 || x >= CarFrame.W_FRAME - 170) {
            x = xR;
        }
    }

    public void fire(ArrayList<Bullet> arr) {
        long T = System.currentTimeMillis();
        if (T - t < 600) {
            return;
        }
        if (this instanceof Car) {
            Sound.play("eat_heart.wav");
        }
        t = T;
        int x = this.x - 10;

        Bullet bullet = new Bullet(x, y);
        arr.add(bullet);
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(images, x, y, null);
    }
}
