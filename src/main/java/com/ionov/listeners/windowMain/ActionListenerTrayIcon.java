package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 28.07.2016.
 */
public class ActionListenerTrayIcon implements ActionListener {
    private JFrame window;

    public ActionListenerTrayIcon(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.setVisible(true);
        window.setExtendedState(JFrame.NORMAL);
    }
}
