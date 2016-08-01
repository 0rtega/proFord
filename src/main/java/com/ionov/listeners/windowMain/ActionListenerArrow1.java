package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 19.07.2016.
 */
public class ActionListenerArrow1 implements ActionListener {
    private JButton arrow;
    private JButton arrow1;
    private JPanel panel;

    public ActionListenerArrow1(JButton arrow, JButton arrow1, JPanel panel) {
        this.arrow = arrow;
        this.arrow1 = arrow1;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setVisible(false);
        arrow.setVisible(true);
        arrow1.setVisible(false);
    }
}
