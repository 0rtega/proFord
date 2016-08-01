package com.ionov.listeners.windowMainStatistic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 25.07.2016.
 */
public class ActionListenerBack implements ActionListener {
    private JPanel panel;

    public ActionListenerBack(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setVisible(false);
    }
}
