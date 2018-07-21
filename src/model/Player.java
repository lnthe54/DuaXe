package model;

import gui.OptionPanel;

import javax.swing.*;

public class Player extends Car {
    private OptionPanel optionPanel = new OptionPanel();
    public Player(int x, int y) {
        super(x, y);
        switch (optionPanel.getChooseCar()){
            case 1:{
                images = new ImageIcon(getClass().getResource("/image/car1.png")).getImage();
                break;
            }
            case 2:{
                images = new ImageIcon(getClass().getResource("/image/car2.png")).getImage();
                break;
            }
            case 3:{
                images = new ImageIcon(getClass().getResource("/image/car3.png")).getImage();
                break;
            }
            default:{
                images = new ImageIcon(getClass().getResource("/image/car1.png")).getImage();
            }
        }
    }

    public void changeOrient(int newOrient){
        orient = newOrient;
    }
}
