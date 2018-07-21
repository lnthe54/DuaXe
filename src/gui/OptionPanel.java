package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel implements ActionListener {
    public int chooseCar;
    private MyContainer container;
    private JLabel lbbackground;
    private ImageIcon backgroundIcon;
    private JButton btn_car1, btn_car2, btn_car3;

    public OptionPanel() {
    }

    public OptionPanel(MyContainer container) {
        this.container = container;
        setLayout(null);
        initCompts();
        initBackgroud();
    }

    private void initBackgroud() {
        lbbackground = new JLabel();
        lbbackground.setBounds(0, 0, CarFrame.W_FRAME, CarFrame.H_FRAME);
        backgroundIcon = new ImageIcon(getClass().getResource("/image/background_option.jpg"));
        lbbackground.setIcon(backgroundIcon);
        add(lbbackground);
    }

    private void initCompts() {
        btn_car1 = setButton(180, 250, "/image/car1.png");
        btn_car2 = setButton(280, 250, "/image/car2.png");
        btn_car3 = setButton(380, 250, "/image/car3.png");

        btn_car1.addActionListener(this);
        btn_car2.addActionListener(this);
        btn_car3.addActionListener(this);

        add(btn_car1);
        add(btn_car2);
        add(btn_car3);
    }

    public JButton setButton(int x, int y, String ImageIcon) {
        JButton button = new JButton();
        ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
        button.setBounds(x, y, 60,120);
        button.setIcon(Icon);
        return button;
    }

    public int getChooseCar() {
        return chooseCar;
    }

    public void setChooseCar(int chooseCar) {
        this.chooseCar = chooseCar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_car1)) {
            container.setShowMenu();
            requestFocus();
            this.setChooseCar(1);
        }
        else if (e.getSource().equals(btn_car2)){
            container.setShowMenu();
            requestFocus();
            this.setChooseCar(2);
        }
        else if (e.getSource().equals(btn_car3)){
            container.setShowMenu();
            requestFocus();
            this.setChooseCar(3);
        }
    }

}
