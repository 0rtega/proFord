package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseListenerStatistic implements MouseListener {
    private JButton statistic;

    public MouseListenerStatistic(JButton createUser) {
        this.statistic = createUser;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (statistic.isEnabled()) {
            statistic.setBackground(Color.WHITE);
            statistic.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (statistic.isEnabled()) {
            statistic.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (statistic.isEnabled()) {
            statistic.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (statistic.isEnabled()) {
            statistic.setFont(new Font("Arial", Font.BOLD, 30));
            statistic.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (statistic.isEnabled()) {
            statistic.setFont(new Font("Arial", Font.BOLD, 30));
            statistic.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }
}
