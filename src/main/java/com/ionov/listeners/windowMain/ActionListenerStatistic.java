package com.ionov.listeners.windowMain;

import com.ionov.windows.WindowMainStatistic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 24.07.2016.
 */
public class ActionListenerStatistic implements ActionListener {
    private String name;
    private JFrame window;

    public ActionListenerStatistic(String name, JFrame window) {
        this.name = name;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new WindowMainStatistic(name, window);
    }
}
