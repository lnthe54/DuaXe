package gui;

import model.Car;
import model.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CarPanel extends JPanel implements Runnable, KeyListener, MouseListener {
    public static boolean IS_RUNNING = true;

    public int speed = 4;
    public MyContainer container;
    private GameManager manager;
    private JLabel lbHome, lbPlay, lbPause;
    private boolean[] flags = new boolean[256];


    public CarPanel(MyContainer container) {
        this.container = container;
        manager = new GameManager();
        manager.initGame();
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        Thread thread = new Thread(this);
        thread.start();

        initComponent();
    }

    private void initComponent() {
        lbHome = setLabel(30, 200, "/image/button_home.png");
        lbPlay = setLabel(30, 260, "/image/button_play.png");
        lbPause = setLabel(30, 320, "/image/button_pause.png");

        lbHome.addMouseListener(this);
        lbPlay.addMouseListener(this);
        lbPause.addMouseListener(this);

        add(lbHome);
        add(lbPlay);
        add(lbPause);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g);
        manager.draw(g2D);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        flags[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        flags[e.getKeyCode()] = false;
    }

    @Override
    public void run() {
        while (true) {
            if (IS_RUNNING) {

                boolean check = manager.checkImpact();
                if (check) {

                    if (flags[KeyEvent.VK_A] == true || flags[KeyEvent.VK_LEFT] == true) {
                        manager.move(Car.LEFT);
                    } else if (flags[KeyEvent.VK_D] == true || flags[KeyEvent.VK_RIGHT] == true) {
                        manager.move(Car.RIGHT);
                    }

//                    boolean checkStar = manager.checkStar();
//                    if (checkStar) {
                    if (flags[KeyEvent.VK_SPACE] == true) {
                        manager.playerFire();
                    }
//                    }
                    manager.itemMove();
                    manager.checkCoin();
                    manager.checkStar();
                } else {
                    int result = manager.saveScore();

                    if (result == JOptionPane.OK_OPTION) {
                        manager.initGame();
                        manager.point = 0;
                        flags = new boolean[256];
                    } else {
                        manager.initGame();
                        container.setShowMenu();
                        IS_RUNNING = false;
                    }
                }
                repaint();
                try {
                    if (manager.point > 30) {
                        Thread.sleep((long) (speed - 0.5));
                    } else if (manager.point > 60) {
                        Thread.sleep((long) (this.speed - 0.5));
                    } else {
                        Thread.sleep(speed);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(lbHome)) {
            manager.initGame();
            manager.point = 0;
            container.setShowMenu();
            IS_RUNNING = false;
        }
        if (e.getSource().equals(lbPlay)) {
            IS_RUNNING = true;
        }
        if (e.getSource().equals(lbPause)) {
            IS_RUNNING = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public JLabel setLabel(int x, int y, String ImageIcon) {
        JLabel label = new JLabel();
        ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
        label.setBounds(x, y, Icon.getIconWidth(), Icon.getIconHeight());
        label.setIcon(Icon);
        return label;
    }
}
