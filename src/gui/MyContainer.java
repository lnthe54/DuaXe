package gui;

import javax.swing.*;
import java.awt.*;

public class MyContainer extends JPanel {
    private static final String MENU = "menu";
    private static final String PLAY_GAME = "play_game";
    private static final String OPTION = "option";
    private static final String HIGH_SCORE = "hight_score";
    private CardLayout cardLayout;
    private CarFrame carFrame;
    private Menu menu;
    private CarPanel carPanel;
    private OptionPanel optionPanel;
    private HighScorePanel highScorePanel;

    public MyContainer(CarFrame carFrame) {
        this.carFrame = carFrame;
        setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        menu = new Menu(this);
        add(menu, MENU);

        carPanel = new CarPanel(this);
        add(carPanel, PLAY_GAME);

        highScorePanel = new HighScorePanel(this);
        add(highScorePanel, HIGH_SCORE);

        optionPanel = new OptionPanel(this);
        add(optionPanel, OPTION);

        setShowMenu();

    }

    public CarFrame getCarFrame() {
        return carFrame;
    }

    public void setShowMenu() {
        cardLayout.show(MyContainer.this, MENU);
        menu.requestFocus();
    }

    public void setShowPlay() {
        cardLayout.show(MyContainer.this, PLAY_GAME);
        carPanel.requestFocus();
    }

    public void setShowHighScore() {
        cardLayout.show(MyContainer.this, HIGH_SCORE);
        highScorePanel.requestFocus();
    }

    public void setShowOption() {
        cardLayout.show(MyContainer.this, OPTION);
        optionPanel.requestFocus();
    }

}
