package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerSelect implements MouseListener {
    private JLabel button;
    private JPanel contentPane;
    private  JButton reset;
    private JButton save;
    private  JButton play;
    private JButton pause;
    private JLabel select;
    private JButton arrowUp;
    private JButton arrowDown;
    private JLabel labelActivity;

    public MouseListenerSelect(JButton arrowDown, JButton arrowUp, JLabel select, JLabel button,
                               JPanel contentPane,
                               JButton reset, JButton save,
                               JButton play, JButton pause, JLabel labelActivity) {
        this.button = button;
        this.arrowUp = arrowUp;
        this.arrowDown = arrowDown;
        this.labelActivity = labelActivity;
        this.select = select;
        this.contentPane = contentPane;
        this.reset = reset;
        this.save = save;
        this.play = play;
        this.pause = pause;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (button.isEnabled()) {
            button.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (button.isEnabled()) {
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            play.setVisible(true);
            play.setEnabled(true);
            pause.setVisible(false);
            save.setVisible(true);
            save.setEnabled(false);
            reset.setVisible(true);
            reset.setEnabled(true);
            select.setVisible(false);
            contentPane.setVisible(false);
            arrowDown.setVisible(true);
            arrowUp.setVisible(false);
           labelActivity.setText(((JLabel) e.getSource()).getText());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (button.isEnabled()) {
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (button.isEnabled()) {
            button.setFont(new Font("Arial", Font.PLAIN, 12));
            button.setBorder(BorderFactory.createEtchedBorder());
        }
    }
}
