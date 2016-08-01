package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 17.07.2016.
 */
public class ActionListenerArrow implements ActionListener {
    private JPanel panel;
    private JButton arrow;
    private JButton arrow1;

    public ActionListenerArrow(JPanel scrollPane, JButton arrow1, JButton arrow) {
        this.panel = scrollPane;
        this.arrow1 = arrow1;
        this.arrow = arrow;
    }

    public ActionListenerArrow(JPanel select) {
        this.panel = select;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setVisible(true);
        arrow.setVisible(false);
        arrow1.setVisible(true);
    }
}
