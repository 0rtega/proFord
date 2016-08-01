package com.ionov.listeners.windowMain;

import com.ionov.windows.WindowAlarmClock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 13.07.2016.
 */
public class ActionListenerAlarm implements ActionListener {
    private JFrame window;

    public ActionListenerAlarm(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new WindowAlarmClock(window);
    }
}
