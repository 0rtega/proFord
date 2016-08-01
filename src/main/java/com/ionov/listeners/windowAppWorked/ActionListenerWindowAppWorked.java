package com.ionov.listeners.windowAppWorked;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionListenerWindowAppWorked implements ActionListener {
    private JFrame frame;

    public ActionListenerWindowAppWorked(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }
}
