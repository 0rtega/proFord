package com.ionov.listeners.windowMain;

import com.ionov.windows.WindowEnterPassword;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerUnlock implements ActionListener {
    private JFrame window;
    private String name;

    public ActionListenerUnlock(JFrame window, String name) {
        this.window = window;
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new WindowEnterPassword(window, name);
    }
}