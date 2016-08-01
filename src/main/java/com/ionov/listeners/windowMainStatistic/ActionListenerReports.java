package com.ionov.listeners.windowMainStatistic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerReports implements ActionListener {
    private JPanel panel;

    public ActionListenerReports(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setVisible(true);
    }
}
