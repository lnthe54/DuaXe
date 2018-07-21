package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
    private CarFrame carFrame;
    private MyContainer container;
    private JLabel lbbackground, lbPlay, lbOption, lbHighScore, lbExit;
    private ImageIcon backgroundIcon;

    public Menu(MyContainer container) {
        this.container = container;
        this.carFrame = container.getCarFrame();
        setLayout(null);
        initComps();
        initBackground();
    }

    public void initComps() {
        lbPlay = setLabel(200, 200, "/image/button_playgame.png");
        lbHighScore = setLabel(200, 260, "/image/button_highscore.png");
        lbOption = setLabel(200, 320, "/image/button_option.png");
        lbExit = setLabel(200, 380, "/image/button_exit.png");

        lbPlay.addMouseListener(mouseAdapter);
        lbHighScore.addMouseListener(mouseAdapter);
        lbOption.addMouseListener(mouseAdapter);
        lbExit.addMouseListener(mouseAdapter);

        add(lbPlay);
        add(lbHighScore);
        add(lbOption);
        add(lbExit);

    }

    public void initBackground() {
        lbbackground = new JLabel();
        lbbackground.setBounds(0, 0, carFrame.getWidth(), carFrame.getHeight());
        lbbackground.setBackground(Color.BLACK);
        backgroundIcon = new ImageIcon(getClass().getResource("/image/background.jpg"));
        lbbackground.setIcon(backgroundIcon);
        add(lbbackground);
    }

    public JLabel setLabel(int x, int y, String ImageIcon) {
        JLabel label = new JLabel();
        ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
        label.setBounds(x, y, Icon.getIconWidth(), Icon.getIconHeight());
        label.setIcon(Icon);
        return label;
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource().equals(lbPlay)) {
                ImageIcon playIcon = new ImageIcon(getClass().getResource("/image/button_playgame1.png"));
                lbPlay.setIcon(playIcon);
            }

            if (e.getSource().equals(lbHighScore)) {
                ImageIcon hightScoreIcon = new ImageIcon(getClass().getResource("/image/button_highscore1.png"));
                lbHighScore.setIcon(hightScoreIcon);
            }

            if (e.getSource().equals(lbOption)) {
                ImageIcon optionIcon = new ImageIcon(getClass().getResource("/image/button_option1.png"));
                lbOption.setIcon(optionIcon);
            }
            if (e.getSource().equals(lbExit)) {
                ImageIcon exitIcon = new ImageIcon(getClass().getResource("/image/button_exit1.png"));
                lbExit.setIcon(exitIcon);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource().equals(lbPlay)) {
                ImageIcon playIcon = new ImageIcon(getClass().getResource("/image/button_playgame.png"));
                lbPlay.setIcon(playIcon);
            }

            if (e.getSource().equals(lbHighScore)) {
                ImageIcon highScoreIcon = new ImageIcon(getClass().getResource("/image/button_highscore.png"));
                lbHighScore.setIcon(highScoreIcon);
            }

            if (e.getSource().equals(lbOption)) {
                ImageIcon optionIcon = new ImageIcon(getClass().getResource("/image/button_option.png"));
                lbOption.setIcon(optionIcon);
            }
            if (e.getSource().equals(lbExit)) {
                ImageIcon exitIcon = new ImageIcon(getClass().getResource("/image/button_exit.png"));
                lbExit.setIcon(exitIcon);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource().equals(lbExit)) {
                System.exit(0);
            }
            if (e.getSource().equals(lbPlay)) {
                container.setShowPlay();
            }
            if (e.getSource().equals(lbHighScore)) {
                container.setShowHighScore();
            }
            if (e.getSource().equals(lbOption)) {
                container.setShowOption();
            }
        }
    };

}	
