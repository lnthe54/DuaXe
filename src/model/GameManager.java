package model;

import gui.CarFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GameManager {
    private static ArrayList<HighScore> arrHighScore;
    private Random rd = new Random();
    private Road road;
    private Player player;
    private ArrayList<BossCar> arrCar;
    private ArrayList<Coins> arrCoin;
    private ArrayList<Collision> arrCollision;
    private ArrayList<Star> arrStar;
    private ArrayList<Bullet> arrBullet;
    public int point = 0;

    public void initGame() {
        road = new Road();
        player = new Player(295, 350);
        arrCar = new ArrayList<>();
        arrCoin = new ArrayList<>();
        arrStar = new ArrayList<>();
        arrCollision = new ArrayList<>();
        arrBullet = new ArrayList<>();
        arrHighScore = new ArrayList<>();

        initCar();
        initCoin();
        initStar();
        initArrHightScore("src/highscore/HighScore.txt");
    }

    private void initStar() {
        if (arrStar.size() < 1) {
            int khoangCach = rd.nextInt(50) + 50;
            arrStar.add(new Star(200 + khoangCach, -200 - khoangCach));
        }
    }

    private void initCoin() {
        if (arrCoin.size() < 2) {
            int khoangCach = rd.nextInt(50);
            arrCoin.add(new Coins(400 - khoangCach, -150 - khoangCach));
            arrCoin.add(new Coins(200 + khoangCach, -300 + khoangCach));
        }
    }

    private void initCar() {
        if (arrCar.size() <= 4) {
            int khoangCach = rd.nextInt(50);
            arrCar.add(new BossCar(135 + khoangCach, -20 - khoangCach));
            arrCar.add(new BossCar(440 - khoangCach, -500 - khoangCach));
            arrCar.add(new BossCar(200 + khoangCach, -300 - khoangCach));
            arrCar.add(new BossCar(300 + khoangCach, -200 - khoangCach));
        }
    }

    public void initArrHightScore(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                String name = str[0];
                int score = Integer.parseInt(str[1]);
                HighScore hightScore = new HighScore(name, score);
                arrHighScore.add(hightScore);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int newOrient) {
        player.changeOrient(newOrient);
        player.move();
    }

    public void itemMove() {
        road.move();

        for (int i = 0; i < arrCoin.size(); i++) {
            arrCoin.get(i).move();
            if (arrCoin.get(i).getY() > CarFrame.H_FRAME - 60) {
                arrCoin.remove(i);
            }
        }

        initCoin();

        if (point >= 25 || point >= 60) {
            for (int i = 0; i < arrStar.size(); i++) {
                arrStar.get(i).move();
            }
        }

        initStar();

        for (int i = arrCar.size() - 1; i >= 0; i--) {
            arrCar.get(i).move();
            if (arrCar.get(i).getY() > CarFrame.H_FRAME - 50) {
                arrCar.remove(i);
            }
        }
        initCar();

        for (int i = arrBullet.size() - 1; i >= 0; i--) {
            boolean check = arrBullet.get(i).move();
            if (check == false) {
                arrBullet.remove(i);
            }
        }
    }

    public void draw(Graphics2D g2D) {
        road.draw(g2D);

        for (int i = 0; i < arrCoin.size(); i++) {
            arrCoin.get(i).draw(g2D);
        }

        for (Bullet bullet : arrBullet) {
            bullet.draw(g2D);
        }

        player.draw(g2D);

        for (int i = 0; i < arrCar.size(); i++) {
            arrCar.get(i).draw(g2D);
        }

        if (point >= 25 || point >= 60) {
            for (int i = 0; i < arrStar.size(); i++) {
                arrStar.get(i).draw(g2D);
            }
        }

        for (int i = arrCollision.size() - 1; i >= 0; i--) {
            boolean check = arrCollision.get(i).draw(g2D);
            if (check == true) {
                arrCollision.remove(i);
            }
        }

        g2D.setColor(Color.RED);
        g2D.setFont(new Font("", Font.BOLD, 18));
        g2D.drawString("POINT : " + point, 260, 100);
    }

    public void playerFire() {
        player.fire(arrBullet);
    }

    public boolean checkImpact() {
        for (int i = 0; i < arrCar.size(); i++) {
            if (arrCar.get(i).impactBossAndPlayer(player)) {
                Sound.play("cham_xe.wav");
                return false;
            }

            boolean die = arrCar.get(i).checkDie(arrBullet);
            if (die == true) {
                Sound.play("cham_xe.wav");
                Collision collision = new Collision(arrCar.get(i).getX() + 100, arrCar.get(i).getY() + 60);
                arrCollision.add(collision);
                arrCar.remove(i);
            }
        }
        return true;
    }

    public boolean checkStar() {
        for (int i = 0; i < arrStar.size(); i++) {
            if (arrStar.get(i).isImpactStarAndPlayer(player)) {
                arrStar.remove(i);
                Sound.play("eat_star.wav");
                point += 10;
                arrCar.clear();
                return true;
            }
        }
        return false;
    }

    public void checkCoin() {
        for (int i = 0; i < arrCoin.size(); i++) {
            if (arrCoin.get(i).impactCoinAndPlayer(player)) {
                arrCoin.remove(i);
                Sound.play("eat_gold.wav");
                point++;
            }
        }

        for (int i = 0; i < arrCoin.size(); i++){
            if (arrCoin.get(i).impactCoinAndBullet(arrBullet)){
                arrCoin.remove(i);
            }
        }
    }

    public int saveScore() {
        System.out.println();
        String name = JOptionPane.showInputDialog("Please input your name !");
        HighScore newScore = new HighScore(name, point);
        arrHighScore.add(newScore);

        Collections.sort(arrHighScore, new Comparator<HighScore>() {

            @Override
            public int compare(HighScore score1, HighScore score2) {
                if (score1.getScore() < score2.getScore()) {
                    return 1;
                } else {
                    if (score1.getScore() == score2.getScore()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });

        if (arrHighScore.size() > 10) {
            arrHighScore.remove(arrHighScore.size() - 1);
        }

        try {
            FileOutputStream fileOutput = new FileOutputStream("src/highscore/HighScore.txt");
            for (int i = 0; i < arrHighScore.size(); i++) {
                String content = arrHighScore.get(i).getName() + ":" + arrHighScore.get(i).getScore() + "\n";
                fileOutput.write(content.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
