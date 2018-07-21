package gui;

import model.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class HighScorePanel extends JPanel implements MouseListener {
    private MyContainer container;
    private JLabel lbBackMenu;
    private ArrayList<HighScore> arrHightScore;

    public HighScorePanel(MyContainer container) {
        this.container = container;
        setLayout(null);
        initCompts();
    }

    public void initCompts() {
        ReadFileHightScore();
        lbBackMenu = setLabel(200, 550, "/image/button_backmenu.png");
        lbBackMenu.addMouseListener(this);
        add(lbBackMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawImage(g2d);
        drawHightSore(g2d);
    }

    public void drawImage(Graphics2D g2d) {
        Image background = new ImageIcon(getClass().getResource("/image/background_option.jpg")).getImage();
        g2d.drawImage(background, 0, 0, null);
    }

    public void drawHightSore(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.RED);
        int y = 150;
        for (int i = 0; i < arrHightScore.size(); i++) {
            g2d.drawString("" + (i + 1), 130, y);
            g2d.drawString("" + arrHightScore.get(i).getName(), 180, y);
            g2d.drawString("" + arrHightScore.get(i).getScore(), 430, y);
            y = y + 40;
        }

    }

    public void ReadFileHightScore() {
        arrHightScore = new ArrayList<HighScore>();
        try {
            FileReader file = new FileReader("src/highscore/HighScore.txt");
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                String name = str[0];
                int score = Integer.parseInt(str[1]);
                HighScore hightScore = new HighScore(name, score);
                arrHightScore.add(hightScore);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JLabel setLabel(int x, int y, String ImageIcon) {
        JLabel label = new JLabel();
        ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
        label.setBounds(x, y, Icon.getIconWidth(), Icon.getIconHeight());
        label.setIcon(Icon);
        return label;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(lbBackMenu)) {
            container.setShowMenu();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(lbBackMenu)) {
            ImageIcon playIcon = new ImageIcon(getClass().getResource("/image/button_backmenu1.png"));
            lbBackMenu.setIcon(playIcon);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lbBackMenu)) {
            ImageIcon playIcon = new ImageIcon(getClass().getResource("/image/button_backmenu.png"));
            lbBackMenu.setIcon(playIcon);
        }
    }
}
