package com.ionov.listeners.windowStartWithAuto;

import com.ionov.windows.WindowCheckIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 11.07.2016.
 */
public class ActionListenerCheckIn implements ActionListener {

    private JFrame window;

    public ActionListenerCheckIn(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new WindowCheckIn(window);
    }
}
