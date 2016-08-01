package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by user on 19.07.2016.
 */
public class MouseListenerButton implements MouseListener {
    private JButton createUser;

    public MouseListenerButton(JButton createUser) {
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
            createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (createUser.isEnabled()) {
            createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (createUser.isEnabled()) {
            createUser.setFont(new Font("Arial", Font.PLAIN, 12));
            createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (createUser.isEnabled()) {
            createUser.setFont(new Font("Arial", Font.PLAIN, 12));
            createUser.setBorder(BorderFactory.createEtchedBorder());
        }
    }
}
