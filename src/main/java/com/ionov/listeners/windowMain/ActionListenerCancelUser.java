package com.ionov.listeners.windowMain;

import com.ionov.windows.WindowClosing;
import com.ionov.windows.WindowClosingUser;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionListenerCancelUser  implements ActionListener{
    private JFrame window;

    public ActionListenerCancelUser(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new WindowClosingUser(window);

    }
}
