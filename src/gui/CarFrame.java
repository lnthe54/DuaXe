package gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CarFrame extends JFrame {
    public static final int W_FRAME = 620;
    public static final int H_FRAME = 750;

    private MyContainer container;

    public CarFrame() {
        setTitle("Car Racing");
        setSize(W_FRAME, H_FRAME);
        setResizable(false);
        setLocationRelativeTo(null);
        container = new MyContainer(this);
        add(container);
        addWindowListener(window);
        setVisible(true);
    }

    private WindowAdapter window = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            CarPanel.IS_RUNNING = false;
        }
    };
}
