package com.ionov.listeners.windowClosingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 12.07.2016.
 */
public class ActionListenerButtonCancel implements ActionListener{
    private JDialog dialog;

    public ActionListenerButtonCancel(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.dispose();
    }
}
