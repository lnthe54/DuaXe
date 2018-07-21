package model;

import javax.swing.*;
import java.awt.*;

public class Bullet extends Object {

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        img = new ImageIcon(getClass().getResource("/image/bullet.png")).getImage();
        speed = 1;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(img,x,y,null);
    }

    public boolean move() {
       y -= speed;
        if (y < 0) {
            return false;
        }
        return true;
    }

    public Rectangle getRect(){
        Rectangle rectangle = new Rectangle(x,y,img.getWidth(null), img.getHeight(null));
        return rectangle;
    }
}
