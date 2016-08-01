package com.ionov.listeners.windowMainStatistic;

import com.ionov.windows.WindowStatisticDays;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 27.07.2016.
 */
public class ActionListenerButtonDate implements ActionListener {
    private JFrame window;
    private JComboBox<String> months;
    private JButton button;
    private String name;

    public ActionListenerButtonDate(String name, JFrame window, JComboBox<String> months, JButton button) {
        this.window = window;
        this.name = name;
        this.months = months;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new WindowStatisticDays(name, window,months, button);
    }
}
