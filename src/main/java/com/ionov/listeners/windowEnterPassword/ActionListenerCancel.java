package com.ionov.listeners.windowEnterPassword;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 13.07.2016.
 */
public class ActionListenerCancel implements ActionListener {
    private JDialog dialog;

    public ActionListenerCancel(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.dispose();
    }
}
