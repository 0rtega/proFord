package com.ionov.listeners.windowClosingUser;

import com.ionov.windows.WindowStartWithAuto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 12.07.2016.
 */
public class ActionListenerButtonOk implements ActionListener {
    private JFrame window;
    private JDialog dialog;

    public ActionListenerButtonOk(JFrame window, JDialog dialog) {
        this.window = window;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.dispose();
        dialog.dispose();
        new WindowStartWithAuto();
    }
}
