package com.ionov.listeners.windowStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by user on 09.07.2016.
 */
public class MouseListenersWindowSrart implements MouseListener {

    private JButton createUser;

    public MouseListenersWindowSrart(JButton createUser) {
        this.createUser = createUser;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (createUser.isEnabled()) {
            createUser.setBackground(Color.WHITE);
            createUser.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (createUser.isEnabled()) {
            createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (createUser.isEnabled()) {
            createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (createUser.isEnabled()) {
           createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (createUser.isEnabled()) {
         createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }
}
