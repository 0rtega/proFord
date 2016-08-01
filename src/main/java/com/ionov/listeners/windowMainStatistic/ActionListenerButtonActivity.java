package com.ionov.listeners.windowMainStatistic;

import com.ionov.windows.WindowStatisticActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionListenerButtonActivity implements ActionListener {
    private JFrame window;
    private JComboBox<String> months1;
    private JButton button;
    private String name;
    private JComboBox<String> activityCombo;

    public ActionListenerButtonActivity(JComboBox<String> activityCombo,String name,JComboBox<String> months1, JButton button, JFrame window) {
        this.window = window;
        this.activityCombo =activityCombo;
        this.months1 = months1;
        this.name = name;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new WindowStatisticActivity(activityCombo, name, window, months1, button);
    }
}
